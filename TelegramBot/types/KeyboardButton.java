/*
 * via Builder design pattern
 */
package types;


/**
 *
 * @author Hamid Shafie Asl
 */
public class KeyboardButton {
    private String text;
    private Boolean request_contact;
    private Boolean request_location; 
    
    public KeyboardButton(KeyBtnObj keyBtnObjMaker) {
        this.text = keyBtnObjMaker.text;  
        this.request_contact = keyBtnObjMaker.request_contact;
        this.request_location = keyBtnObjMaker.request_location;
    }
    
    
    public String KeyBtnObjStr () {
        // https://core.telegram.org/bots/api#keyboardbutton
        String lastTxt = "{\"text\":\"" + this.text + "\"";
        if(this.request_contact)
            lastTxt += ",\"request_contact\":true";
        if(this.request_location)
            lastTxt += ",\"request_location\":true";
        return lastTxt + "}";
        //https://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily
        //https://stackoverflow.com/questions/27577922/how-to-pass-a-json-array-as-a-parameter-in-url
    }
  

    public static class KeyBtnObj {
        private String text;
        private Boolean request_contact = false;
        private Boolean request_location = false;
        
        public KeyBtnObj(String text) {
            this.text = text;
        }
        
        public KeyBtnObj reqCon(Boolean request_contact){
            this.request_contact = request_contact;
            return this;
        }
        
        public KeyBtnObj reqLoc(Boolean request_location){
            this.request_location = request_location;
            return this;
        }    
        
        public KeyboardButton BuildKeyboardButton() {
            return new KeyboardButton(this);
        }
        
    }
}

