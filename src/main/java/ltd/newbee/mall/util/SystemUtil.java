package ltd.newbee.mall.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
public class SystemUtil {

    private SystemUtil() {
    }


    /**
     * 登錄或註冊成功后,產生保持使用者登錄狀態會話token值
     *
     * @param src:為使用者最新一次登錄時的now()+user.id+random(4)
     * @return
     */
    public static String genToken(String src) {
        if (null == src || "".equals(src)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            String result = new BigInteger(1, md.digest()).toString(16);
            if (result.length() == 31) {
                result = result + "-";
            }
            System.out.println(result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
