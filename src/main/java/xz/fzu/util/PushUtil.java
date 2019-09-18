package xz.fzu.util;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/18 11:08
 */
public class PushUtil {
    private static final String APP_SECRET_KEY = "ypw8y82QbVNfrIvUz6w09A==";
    private static final String MY_PACKAGE_NAME = "com.djylrz.xzpt";
    private static volatile PushUtil instance = null;

    private PushUtil() {
    }

    public static PushUtil getInstance() {
        if (instance == null) {
            synchronized (PushUtil.class) {
                if (instance == null) {
                    instance = new PushUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 推送消息到客户端
     *
     * @param alias       用户id，使用别名的方式发送
     * @param title       推送消息的标题
     * @param description 推送消息的具体内容
     * @param payload     推送消息的有效载荷
     * @return void
     * @author Murphy
     * @date 2019/5/18 10:48
     */
    public void push(List<String> alias, String title, String description, String payload) throws IOException, ParseException {
        Constants.useOfficial();
        Sender sender = new Sender(APP_SECRET_KEY);
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(payload)
                .restrictedPackageName(MY_PACKAGE_NAME)
                // 使用默认提示音提示
                .notifyType(1)
                .build();
        //根据useraccount, 发送消息到指定设备上
        if (alias.size() == 1) {
            sender.sendToAlias(message, alias.get(0), 3);
        } else {
            sender.sendToAlias(message, alias, 3);
        }
    }
}
