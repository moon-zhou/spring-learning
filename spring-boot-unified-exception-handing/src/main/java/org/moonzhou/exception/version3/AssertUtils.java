package org.moonzhou.exception.version3;

import org.apache.commons.lang3.StringUtils;
import org.moonzhou.constant.ErrorCodeEnum;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类，用于基本的判断，依赖spring的org.springframework.util.Assert
 * <p>
 * 与第二个版本相比，断言的过程，抽象出公告的模板方法（见仁见智，简单的方法里是否有必要？）
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/14 16:30
 * @since 1.0
 */
public class AssertUtils {

    private AssertUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 断言true，可以用自定义的message
     * <p>
     * 同时重载多种参数，因为开发过程中，未及时规约接口使用情况或者是不同的业务逻辑层判断，无法统一数据结构的情况导致
     *
     * @param expValue
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void isTrue(final boolean expValue, final ErrorCodeEnum errorCodeEnum,
                              final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {
                Assert.isTrue(expValue, resultMsg);
            }
        }, errorCodeEnum.getResponseCode(), resultMsg);
    }

    public static void isTrue(final boolean expValue, final ErrorCodeEnum errorCodeEnum) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {
                Assert.isTrue(expValue, errorCodeEnum.getResponseMessage());
            }
        }, errorCodeEnum);
    }

    public static void isTrue(final boolean expValue, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {
                Assert.isTrue(expValue, msg);
            }
        }, code, msg);
    }

    /**
     * 断言false，可以用自定义的message
     * <p>
     * 同时重载多种参数，因为开发过程中，未及时规约接口使用情况或者是不同的业务逻辑层判断，无法统一数据结构的情况导致
     *
     * @param expValue
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void isFalse(final boolean expValue, final ErrorCodeEnum errorCodeEnum,
                               final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {
                Assert.isTrue(!expValue, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void isFalse(final boolean expValue, final ErrorCodeEnum errorCodeEnum) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {
                Assert.isTrue(!expValue, errorCodeEnum.getResponseMessage());
            }
        }, errorCodeEnum);
    }

    public static void isFalse(final boolean expValue, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {
                Assert.isTrue(!expValue, msg);
            }
        }, code, msg);
    }

    /**
     * equals比较，兼容都为null的情况，都为null不比较，不会抛出异常
     *
     * @param obj1
     * @param obj2
     * @param errorCodeEnum
     * @param resultMsg
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void equals(final Object obj1, final Object obj2, final ErrorCodeEnum errorCodeEnum,
                              final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (obj1 == null) {

                    Assert.isNull(obj2, resultMsg);
                    return;
                }

                Assert.isTrue(obj1.equals(obj2), resultMsg);
            }
        }, errorCodeEnum, resultMsg);

    }

    public static void equals(final Object obj1, final Object obj2, final ErrorCodeEnum errorCodeEnum) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (obj1 == null) {

                    Assert.isNull(obj2, errorCodeEnum.getResponseMessage());
                    return;
                }

                Assert.isTrue(obj1.equals(obj2), errorCodeEnum.getResponseMessage());
            }
        }, errorCodeEnum);
    }

    public static void equals(final Object obj1, final Object obj2, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (obj1 == null) {

                    Assert.isNull(obj2, msg);
                    return;
                }

                Assert.isTrue(obj1.equals(obj2), msg);
            }
        }, code, msg);
    }

    /**
     * notEquals比较，兼容都为null的情况，都为null不比较，不会抛出异常
     *
     * @param obj1
     * @param obj2
     * @param errorCodeEnum
     * @param resultMsg
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void notEquals(final Object obj1, final Object obj2, final ErrorCodeEnum errorCodeEnum,
                                 final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (obj1 == null) {

                    Assert.isNull(obj2, resultMsg);
                    return;
                }

                Assert.isTrue(!obj1.equals(obj2), resultMsg);
            }
        }, errorCodeEnum, resultMsg);

    }

    public static void notEquals(final Object obj1, final Object obj2, final ErrorCodeEnum errorCodeEnum) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (obj1 == null) {

                    Assert.isNull(obj2, errorCodeEnum.getResponseMessage());
                    return;
                }

                Assert.isTrue(!obj1.equals(obj2), errorCodeEnum.getResponseMessage());
            }
        }, errorCodeEnum);
    }

    public static void notEquals(final Object obj1, final Object obj2, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (obj1 == null) {

                    Assert.isNull(obj2, msg);
                    return;
                }

                Assert.isTrue(!obj1.equals(obj2), msg);
            }
        }, code, msg);
    }

    /**
     * 一个对象与一个数组对象里的对象进行比较，是否其中一个与之相等（equals）
     * <p>
     * objects==null 则抛异常
     * <p>
     * obj1与其中之一都为null，或者equals相等，则不抛出异常
     *
     * @param obj1
     * @param objects
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void equalsAny(final Object obj1, final Object[] objects,
                                 final ErrorCodeEnum errorCodeEnum, final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notNull(objects, resultMsg);
                boolean hasEqual = false;

                for (Object obj2 : objects) {

                    if (obj1 == null) {

                        if (obj2 == null) {
                            hasEqual = true;
                            break;
                        }
                        continue;
                    }

                    if (obj1.equals(obj2)) {
                        hasEqual = true;
                        break;
                    }
                }

                Assert.isTrue(hasEqual, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void equalsAny(final Object obj1, final Object[] objects, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notNull(objects, msg);
                boolean hasEqual = false;

                for (Object obj2 : objects) {

                    if (obj1 == null) {

                        if (obj2 == null) {
                            hasEqual = true;
                            break;
                        }
                        continue;
                    }

                    if (obj1.equals(obj2)) {
                        hasEqual = true;
                        break;
                    }
                }

                Assert.isTrue(hasEqual, msg);
            }
        }, code, msg);
    }

    /**
     * 为同一对象 ==比较
     *
     * @param obj1
     * @param obj2
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void is(final Object obj1, final Object obj2, final ErrorCodeEnum errorCodeEnum,
                          final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(obj1 == obj2, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void is(final Object obj1, final Object obj2, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(obj1 == obj2, msg);
            }
        }, code, msg);
    }

    /**
     * 不是一个对象，使用!=比较
     *
     * @param obj1
     * @param obj2
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void isNot(final Object obj1, final Object obj2, final ErrorCodeEnum errorCodeEnum,
                             final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(obj1 != obj2, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void isNot(final Object obj1, final Object obj2, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(obj1 != obj2, msg);
            }
        }, code, msg);
    }

    /**
     * obj1是否在objs里面，使用==比较
     * <p>
     * objs不能为null，否则抛异常
     *
     * @param obj1
     * @param objs
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void in(final Object obj1, final Object[] objs, final ErrorCodeEnum errorCodeEnum,
                          final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notNull(objs, resultMsg);
                boolean hasEqual = false;

                for (Object obj2 : objs) {

                    if (obj1 == obj2) {
                        hasEqual = true;
                        break;
                    }
                }

                Assert.isTrue(hasEqual, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void in(final Object obj1, final Object[] objs, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notNull(objs, msg);
                boolean hasEqual = false;

                for (Object obj2 : objs) {

                    if (obj1 == obj2) {
                        hasEqual = true;
                        break;
                    }
                }

                Assert.isTrue(hasEqual, msg);
            }
        }, code, msg);
    }

    /**
     * obj1不在objs中，使用==比较
     *
     * objs为null则直接返回，为真
     *
     * @param obj1
     * @param objs
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void notIn(final Object obj1, final Object[] objs,
                             final ErrorCodeEnum errorCodeEnum, final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (null == objs) {
                    return;
                }

                for (Object obj2 : objs) {

                    Assert.isTrue(obj1 != obj2, resultMsg);
                }
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void notIn(final Object obj1, final Object[] objs, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                if (null == objs) {
                    return;
                }

                for (Object obj2 : objs) {

                    Assert.isTrue(obj1 != obj2, msg);
                }
            }
        }, code, msg);
    }

    /**
     * 字符串为blank，否则异常
     * @param str
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void blank(final String str, final ErrorCodeEnum errorCodeEnum,
                             final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(StringUtils.isBlank(str), resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }
    public static void blank(final String str, final ErrorCodeEnum errorCodeEnum) {

        blank(str, errorCodeEnum, errorCodeEnum.getResponseMessage());
    }
    public static void blank(final String str, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(StringUtils.isBlank(str), msg);
            }
        }, code, msg);
    }

    /**
     * 字符串不是blank，否则断言抛异常
     * @param str
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void notBlank(final String str, final ErrorCodeEnum errorCodeEnum,
                                final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(StringUtils.isNotBlank(str), resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }
    public static void notBlank(final String str, final ErrorCodeEnum errorCodeEnum) {

        notBlank(str, errorCodeEnum.getResponseCode(), errorCodeEnum.getResponseMessage());
    }
    public static void notBlank(final String str, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(StringUtils.isNotBlank(str), msg);
            }
        }, code, msg);
    }

    /**
     * 断言对象为null，否则抛异常
     * @param object
     * @param errorCodeEnum
     */
    public static void isNull(final Object object, final ErrorCodeEnum errorCodeEnum) {

        isNull(object, errorCodeEnum.getResponseCode(), errorCodeEnum.getResponseMessage());
    }
    public static void isNull(final Object object, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isNull(object, msg);
            }
        }, code, msg);
    }

    /**
     * 断言不为null
     * @param object
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void notNull(final Object object, final ErrorCodeEnum errorCodeEnum,
                               final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notNull(object, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void notNull(final Object object, final ErrorCodeEnum errorCodeEnum) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notNull(object, errorCodeEnum.getResponseMessage());
            }
        }, errorCodeEnum.getResponseCode(), errorCodeEnum.getResponseMessage());
    }
    public static void notNull(final Object object, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notNull(object, msg);
            }
        }, code, msg);
    }

    /**
     * 断言集合不为空
     * @param collection
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void notEmpty(final Collection collection, final ErrorCodeEnum errorCodeEnum,
                                final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notEmpty(collection, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void notEmpty(final Collection collection, final ErrorCodeEnum errorCodeEnum) {

        notEmpty(collection, errorCodeEnum, errorCodeEnum.getResponseMessage());
    }

    /**
     * 断言集合为空，CollectionUtils与Assert一致，使用spring的工具类
     * @param collection
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void empty(final Collection collection, final ErrorCodeEnum errorCodeEnum,
                             final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(CollectionUtils.isEmpty(collection), resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }
    @SuppressWarnings("rawtypes")
    public static void empty(final Collection collection, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(CollectionUtils.isEmpty(collection), msg);
            }
        }, code, msg);
    }

    /**
     * map不为空的断言
     *
     * 因为map与collection不是一个父类，所以需要单独方法判断，不能共用一个方法
     *
     * @param map
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void notEmpty(final Map map, final ErrorCodeEnum errorCodeEnum,
                                final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notEmpty(map, resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }

    public static void notEmpty(final Map map, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.notEmpty(map, msg);
            }
        }, code, msg);
    }

    /**
     * map为空的断言
     *
     * 因为map与collection不是一个父类，所以需要单独方法判断，不能共用一个方法
     *
     * @param map
     * @param errorCodeEnum
     * @param resultMsg
     */
    public static void empty(final Map map, final ErrorCodeEnum errorCodeEnum,
                             final String resultMsg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(CollectionUtils.isEmpty(map), resultMsg);
            }
        }, errorCodeEnum, resultMsg);
    }
    public static void empty(final Map map, String code, String msg) {

        check(new AssertTemplate() {

            @Override
            public void doAssert() {

                Assert.isTrue(CollectionUtils.isEmpty(map), msg);
            }
        }, code, msg);
    }

    /**
     * 使用模板方法
     *
     * @param assertTemplate
     * @param errorCodeEnum
     */
    private static void check(AssertTemplate assertTemplate, ErrorCodeEnum errorCodeEnum) {

        try {

            assertTemplate.doAssert();

            // spring断言出的异常，转换为自定义系统的业务异常
        } catch (IllegalArgumentException e) {

            throw new AppException(errorCodeEnum.getResponseCode(), errorCodeEnum.getResponseMessage(), e);
        }
    }

    private static void check(AssertTemplate assertTemplate, ErrorCodeEnum errorCodeEnum, String resultMsg) {

        try {

            assertTemplate.doAssert();

            // spring断言出的异常，转换为自定义系统的业务异常
        } catch (IllegalArgumentException e) {

            throw new AppException(errorCodeEnum.getResponseCode(), resultMsg, e);
        }
    }

    /**
     * 使用模板方法
     *
     * @param assertTemplate
     * @param code
     * @param msg
     */
    private static void check(AssertTemplate assertTemplate, String code, String msg) {

        try {

            assertTemplate.doAssert();

            // spring断言出的异常，转换为自定义系统的业务异常
        } catch (IllegalArgumentException e) {

            throw new AppException(code, msg, e);
        }
    }

    /**
     * 抽象校验接口模板
     */
    private static interface AssertTemplate {

        public void doAssert();

    }

}
