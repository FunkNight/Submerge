package me.FunkNight.Utils;

import me.FunkNight.Utils.HWIDUtlis;
import me.FunkNight.Utils.WebUtils;
import me.FunkNight.Utils.SystemUtils;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FunkNightHWIDCheck {
    public static String ClientName = "Submerge";

    public static void LoadingTitle() {
        Display.setTitle(ClientName + " 正在启动中");
    }
    public static void Title() {
        Display.setTitle(ClientName + " | " + version + " | 欢迎用户: " + FunkNightHWIDCheck.username + " | 无处不在的时空，才是世界唯一的历史。");
    }

    public static String Cracked = "Submerge - Cracked - Power";
    public static String version = "1.1.2.0";
    public static String username;
    public static boolean Finish;

    public static void sendWindowsMessageLogin() throws AWTException, IOException {
        SystemUtils.displayTray("请输入账号密码", "Login", TrayIcon.MessageType.WARNING);
        String AT = JOptionPane.showInputDialog("请输入账号");
        final int R = 0;
        FunkNightHWIDCheck.username = AT;

        try {
            if (username == null) {
                JOptionPane.showMessageDialog(null, "用户名不能为空!", "验证", 0);
                System.exit(0);
            } else {
                if (WebUtils.get("https://gitee.com/funknightmai/Submerge/blob/master/SubmergeHWID.json")
                        .contains(HWIDUtlis.getHWID())) {
                    JOptionPane.showMessageDialog(null, "登陆成功", "Checker", 1);
                    SystemUtils.displayTray("登陆成功", "正在启动", TrayIcon.MessageType.WARNING);
                    FunkNightHWIDCheck.Finish = true;
                } else {
                    JOptionPane.showMessageDialog(null, "登陆失败", "Checker", 0);
                    JOptionPane.showInputDialog(null, "您还未绑定HWID,请绑定后重试！", HWIDUtlis.getHWID());
                    SystemUtils.displayTray("您还未绑定HWID,请绑定后重试！", "您还未绑定HWID,请绑定后重试！", TrayIcon.MessageType.WARNING);
                    System.exit(0);
                }
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            JOptionPane.showMessageDialog(null, "连接服务器失败,无效的登录会话");
            SystemUtils.displayTray("连接服务器失败,无效的登录会话", "连接服务器失败,无效的登录会话", TrayIcon.MessageType.WARNING);
            System.exit(0);
        }
    }
}
