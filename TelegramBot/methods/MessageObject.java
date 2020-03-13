/*
 * https://javatutorial.net/java-builder-design-pattern
 * 
 * 
 */
package methods;

import types.ReplyKeyboardMarkup;
/**
 *
 * @author Hamid Shafie asl
 */
public class MessageObject
{
    private Long chat_id;
    private String text;
    private String parse_mode;
    private Boolean disable_web_page_preview;
    private Boolean disable_notification;
    private Long reply_to_message_id;
    private ReplyKeyboardMarkup reply_markup;
    
    public MessageObject(MessageObjectBuilder messageObjectBuilder) {
        this.chat_id = messageObjectBuilder.chat_id;
        this.text = messageObjectBuilder.text;
        
        this.parse_mode = messageObjectBuilder.parse_mode;
        this.disable_web_page_preview = messageObjectBuilder.disable_web_page_preview;
        this.disable_notification = messageObjectBuilder.disable_notification;
        this.reply_to_message_id = messageObjectBuilder.reply_to_message_id;
        this.reply_markup = messageObjectBuilder.reply_markup;
    }
    
    // A last method to send message!
    public String endPoint(String token) {
        String str = "https://api.telegram.org/bot" + token 
                + "/sendMessage?chat_id=" + chat_id + "&text=" + text;
        if(!parse_mode.isEmpty())
            str +="&parse_mode=" + parse_mode;
        if(disable_web_page_preview)
            str +="&disable_web_page_preview=true";   
        if(disable_notification)
            str +="&disable_notification=true";    
        if(reply_to_message_id >= 0)
            str +="&reply_to_message_id=" + reply_to_message_id;   
        if(reply_markup != null)
            str += "&reply_markup=" + reply_markup.RepKeyMarObjStr();
        return str;
    }
    

    public static class MessageObjectBuilder {
        private Long chat_id;
        private String text;
        private String parse_mode = "";
        private Boolean disable_web_page_preview = false;
        private Boolean disable_notification = false;
        private Long reply_to_message_id =  -1L;
        private ReplyKeyboardMarkup reply_markup = null;
        
        public MessageObjectBuilder(Long chat_id, String text) {
            this.chat_id = chat_id;
            this.text = text;
        }
        
        public MessageObjectBuilder withPM(String parse_mode) {
            this.parse_mode = parse_mode;
            return this;
        }
        
        public MessageObjectBuilder withDWPP(Boolean disable_web_page_preview) {
            this.disable_web_page_preview = disable_web_page_preview;
            return this;
        }
        
        public MessageObjectBuilder withBDN(Boolean disable_notification) {
            this.disable_notification = disable_notification;
            return this;
        } 
        
        public MessageObjectBuilder withRTMI(Long reply_to_message_id) {
            this.reply_to_message_id = reply_to_message_id;
            return this;
        } 

        public MessageObjectBuilder withRPM(ReplyKeyboardMarkup reply_markup) {
            this.reply_markup = reply_markup;
            return this;
        }         
        
        public MessageObject buildMessageObject() {
            return new MessageObject(this);
        }        
    } 
}

