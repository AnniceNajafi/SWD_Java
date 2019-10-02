package RequestInheritance;

import java.util.UUID;

public class Request {
    private static int count;
    private UUID uuid;

    Request(UUID uuid) {
        this.uuid = uuid;
        count++;
    }

    public static int count() {
        return count;
    }

    public static class GetRequest extends Request{
        String url;
        static int GetReqcount;
        GetRequest(UUID uuid, String url){
            super(uuid);
            this.url=url;
            GetReqcount++;

        }
        public static int count(){
            return GetReqcount;
        }
    }
    public static class PostRequest extends Request {
        String IP;
        static int PostReqcount;
        PostRequest(UUID uuid, String IP) {
            super(uuid);
            this.IP = IP;
            PostReqcount++;
        }
        public static int count(){
            return PostReqcount;
        }
    }

    public static class PostFormRequest extends PostRequest {
        Form form;
        static int PostFormReqcount;

        PostFormRequest(UUID uuid, String IP, Form form) {
            super(uuid, IP);
            this.form = form;
            PostFormReqcount++;
        }
        public static int count(){
            return PostFormReqcount;
        }
    }

    public static class PostEncryptedFormRequest extends PostFormRequest {
        String encryptionScheme;
        static int PostEncFormReqcount;
        PostEncryptedFormRequest(UUID uuid, String IP, Form form, String encryptionScheme) {
            super(uuid, IP, form);
            this.encryptionScheme = encryptionScheme;
            PostEncFormReqcount++;
        }
        public static int count(){
            return PostEncFormReqcount;
        }

    }

    public static class PostPaymentRequest extends PostRequest {
        Payment payment;
        static int PostPayReqcount;
        PostPaymentRequest(UUID uuid, String IP, Payment payment) {
            super(uuid, IP);
            this.payment = payment;
            PostPayReqcount++;
        }
        public static int count(){
            return PostPayReqcount;
        }
    }

    public static class PostEncryptedPaymentRequest extends PostPaymentRequest {
        String encryptionScheme;
        static int PostEncPayReqcount;
        PostEncryptedPaymentRequest(UUID uuid, String IP, Payment payment, String encryptionScheme) {
            super(uuid, IP, payment);
            this.encryptionScheme = encryptionScheme;
            PostEncPayReqcount++;
        }
        public static int count(){
            return PostEncPayReqcount;
        }
    }

    public static class GetFileRequest extends Request {
        File file;
        static int GetFileReqcount;
        GetFileRequest(UUID uuid, File file) {
            super(uuid);
            this.file = file;
            GetFileReqcount++;
        }
        public static int count(){
            return GetFileReqcount;
        }
    }

    public static class GetVideoRequest extends Request {
        Video video;
        static int GetVidReqcount;
        GetVideoRequest(UUID uuid, Video video) {
            super(uuid);
            this.video = video;
            GetVidReqcount++;
        }
        public static int count(){
            return GetVidReqcount;
        }
    }
}

