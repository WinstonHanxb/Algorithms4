package extras.MultiThread.DesignPattern.FuturePattern;

class Main {
    public static void main(String[] args) {
        Client cl = new Client();
        Data data = cl.request("请求参数");
        System.out.println("请求发送成功！");
        System.out.println("做其他事情…………");

        String result = data.getRequest();
        System.out.println(result);
    }
}
