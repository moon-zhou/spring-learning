package org.moonzhou.spring.ioc.condition.injection.demo001os.impl;

import org.moonzhou.spring.ioc.condition.injection.demo001os.ShowCmd;

public class LinuxShowCmd implements ShowCmd {
    @Override
    public String showCmd() {
        return "ls";
    }
}