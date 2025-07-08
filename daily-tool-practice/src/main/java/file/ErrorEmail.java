package file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorEmail {
    public static void main(String[] args) {
        String path = "D:\\error1.txt";
        Pattern pattern = Pattern.compile("系统上线通知 邮箱: \\[(.*?)\\]");
        // 编译正则表达式
        String timeRegex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        Pattern pattern3 = Pattern.compile(timeRegex);
        List<Recipient> recipients = new ArrayList<>();
        int cnt = 0;
        try (FileWriter writer = new FileWriter("C:\\Users\\Administrator\\Desktop\\send_email.txt")) {
            String line;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
                while ((line = reader.readLine()) != null) {
                    if (line.contains("发送邮件失败, 主题: 系统上线通知")) {
                        // 查找匹配的recipientsTOList
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            // 写入匹配到的结果
                            String email = matcher.group(1);
                            System.out.println(email);
                            Recipient recipient = new Recipient(email);
                            Matcher matcher3 = pattern3.matcher(line);
                            if (matcher3.find()) {
                                recipient.setTime(matcher3.group());
                            }
                            recipients.add(recipient);
                            cnt++;
                        }
                    }

                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        WriteSheet writeSheet = EasyExcel.writerSheet("收件人列表").head(Recipient.class).build();
        EasyExcel.write("D:\\ERROR.xlsx")
                .sheet("收件人列表")
                .head(Recipient.class)
                .doWrite(recipients);
        System.out.println("最终的数量是："+cnt+"个");
    }

    static class Recipient {
        @ExcelProperty("收件人")
        private String recipient;

        @ExcelProperty("账号")
        private String account;

        @ExcelProperty("时间")
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Recipient(String recipient) {
            this.recipient = recipient;
        }
        public String getRecipient() {
            return recipient;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }
    }
}
