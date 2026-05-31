package org.moon.sensitive;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author zhaoccf
* @version 1.0
* @description 自定义日志信息处理，进行名称脱敏处理
*/
public class SensitiveFilterPatternLayout extends PatternLayout {
   private static final String REGEX_PRE = "\"";
   // private static final String REGEX_SUF = "":"[\\u4e00-\\u9fa5\\w\\s-·•\\/]+"";
   private static final String REGEX_SUF = "";
   private static final String REGEX_COMMA = ",{2,}";
   private static final String REGEX_COMMA_PRE = "\\{,";
   private static final String REGEX_COMMA_SUF = ",\\}";
   private static List<Pattern> patterns = new ArrayList<>();
   private static String filterPropertyNames;
   public static String newFilterPropertyNames;

   public SensitiveFilterPatternLayout() {
       //patterns已初始化完成并且未修改配置文件，直接返回
       if (!ObjectUtils.isEmpty(newFilterPropertyNames) && newFilterPropertyNames.equals(filterPropertyNames)) {
           return;
       }
       //配置文件未加载
       if (ObjectUtils.isEmpty(newFilterPropertyNames) && ObjectUtils.isEmpty(filterPropertyNames)) {
           return;
       }
       //开始初始化patterns，配置修改newFilterPropertyNames后重新设置filterPropertyNames用于初始化判断并更新正则表达式patterns
       if (!ObjectUtils.isEmpty(newFilterPropertyNames)) {
           filterPropertyNames = newFilterPropertyNames;
           patterns.clear();
       }
       System.out.println("init filterPropertyNames:".concat(filterPropertyNames));

       //拼接匹配正则表达式
       Arrays.asList(filterPropertyNames.split(",")).stream().forEach(name -> {
           String regexStr = new StringBuffer(REGEX_PRE).append(name).append(REGEX_SUF).toString();
           patterns.add(Pattern.compile(regexStr));
       });
   }

   @Override
   public String doLayout(ILoggingEvent event) {
       String message = super.doLayout(event);
       Set<String> matches = new HashSet<>();
       for (Pattern pattern : patterns) {
           Matcher matcher = pattern.matcher(message);
           while (matcher.find()) {
               for (int i = 0; i <= matcher.groupCount(); i++) {
                   matches.add(matcher.group(i));
               }
           }
       }
       //因为字段可能在最后一个也可能不在，存在{"productSortName":"分配zcc"}和{"productSortName":"分配zcc",}两种匹配项导致替换后出现多个逗号情况
       //如果为""不做过滤替换因为没有数据
       for (String match : matches) {
           message = message.replaceAll(match, "");
       }
       Pattern comma = Pattern.compile(REGEX_COMMA);
       Matcher commaMatcher = comma.matcher(message);
       if (commaMatcher.find()) {
           message =  commaMatcher.replaceAll(",");
       }

       Pattern commaPre = Pattern.compile(REGEX_COMMA_PRE);
       Matcher commaPreMatcher = commaPre.matcher(message);
       if (commaPreMatcher.find()) {
           message =  commaPreMatcher.replaceAll("{");
       }

       Pattern commaSuf = Pattern.compile(REGEX_COMMA_SUF);
       Matcher commaSufMatcher = commaSuf.matcher(message);
       if (commaSufMatcher.find()) {
           message =  commaSufMatcher.replaceAll("}");
       }
       return message;
   }

   private boolean containFilterName(String msg) {
       return Arrays.asList(filterPropertyNames.split(",")).stream().anyMatch(item -> msg.contains(item));
   }
}