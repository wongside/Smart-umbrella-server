package side;

public class SystemMessage {
    private static String message = "\nSystem Started.";

    public static String getMessage() {
        try {
            return message;
        } finally {
            SystemMessage.message = "";
        }
    }

    public static void setMessage(String message) {
        SystemMessage.message += "\n" + message;
    }
}
