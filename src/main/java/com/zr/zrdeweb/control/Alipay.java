package com.zr.zrdeweb.control;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.AlipayConfig;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;
import java.util.ArrayList;
import java.util.List;
@Controller
public class Alipay {

    @GetMapping("/alipay/test")
    public void pay() throws AlipayApiException {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCogGEh4ibwmCzHYWnpZuTBcHyC9zGlqPw/uKqvNWKS0WW2zW1Po64CwAQskjySyqRvaPCRdE9tyLHLSbNJAU4TQvXMaYGb3BxusSrigGfmJgamr1Xpf+eCAto8eZZ+9hoxqaPKeMcAlrtjKqyhYv3cK/D6vF0wWrC32n5Y2hEP/T//U/APVu+YoqliyIwQlroxdegi62Ek5UPEAThWNuhS/7c3taTm/+eDVojy/leJMPdGfFz/RmVnZREoNghSGQUmnnlmQBvXzS4ST9PvtGfaXktrRUNH6DUT9GjwIOduQRZt/I1h3vpzpc0qm0CLiCvp0TzuUilHPoUnLnaZdLIDAgMBAAECggEBAJuHG6CVyJFsNHC3xnVrxpuRwytzFL0uk94RCmJ8KQcRBgxL4qiOkM/zAidNt2Fab5pxxxLKw4G2q/zSgwxQiwsWVCAgBkShTxEQsF+uW7g/wwXZcrf6meoTzfHh3HpFl/nc9dSt1CkD9+RZgEvaPi0Z1ezpLi6ylsisgNBsPzF8c5fKV0/sfcZ6ghJ0rz5jxx8FRpr8H0vMDNmnLDAWr+hawUq7CWdIw1an/XWJR8jDoEJfC4IijowiKlNnxXPWg6BtmbI8byitleyC1jdiZSXKWHBXf2Z7sKlYi84u82Dme3IJjwy9nGyFnRBg89FzKgrHp6G8hy919U2tiJJ9reECgYEA5DdKdkB+Ds6GeJ66c/+fve1/Zvb8R7C41HGYRxKJmwaDhDFgqPMyqy2naUIuqHyOpcL5ds0yADFkKv9qpJgUvjfantnX9dZqtqACK7jFEtFSGubW0M0a5mhvCwrtLG4ZtVtBmG1ZeuCQPb7fztUg/seQ0QPGXwuy0KPmXXbsLFECgYEAvQP+FkPjn97GITaYme80rHIoel+1uGahLVDtujiuwxN8PSiNAd4MYAAh0eUqt7zOxHKDocEgWz7uGkdXRYdKq7gEvAguOzyvWxqpxLqmb/aGsXkTwGVQyIbs+Yz6VmSgjKGcuzZGqiTegv3K1ulU107xdnX1AdQx0VWitqSE6BMCgYBs92l6ZPjLoRjb5w4n7NodGZVHWI7EvEDETyjiZpuFheqt59W7VcPPsBDLVWpp0G9b/miWrQs+xFaLoUEOLf9Zg9nDF0jifOqfIG9o6ORQQ9nsNmRDdmttsFe08hakrD1iNpxvZ2beIPM+SDEc1O7YN/mRkzfYkgFjVZ7c6beCwQKBgENTmL31kK7xe0F5YQHz4p9KSoDMdr4oUGED4qniZt21oxtlxlRT87R+V42cQ4sPBdeTsq97Szs1Pzq9DqkFd+OSuJ0BjPYst4mMBcy9b2uSNIgiyJZPgGbtKJv7S3Bah5WQO3Oz8ZfJajjejdHfawkGZZv8iDkOjHA0QLEuCOlhAoGAUY8x+VdwcOa50qtL1PhxNfBKTL+vCwS10MWggxHhvX68wcdHVxiQtXhRPK5bR74ntDg1TmsxJpAT9htI7EvCZCUVNhrMuw4lIoRt9O6t4R1WRdKe1gWprxXRGz3sDyQsbumA1HOpfHrJQDqIUGM7erR9hAlsfCpAmKsyWOSbWCI=";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3ousvx1QUK4Gvi2hpPuFOED+nYWNXYOR6hDIDL2EGRSjMMGgsA0bJtjMiTy2XR6mtlf2aFyT72BTk53S4/JBsYBqWVoGUWejhtzPnUYuN7rN+cDSs1wLsDUxyUszmXKs0a1mHdv+rogP1044GyeGJKetQzE2C3Mbi/qlDykyL3dmA2MmkoxByGHQ3xakwTVZDSuzBFCE1Qe1OCH8iWzsT88Y6SvtIxPiozwdo2HC7VHf5dHHD0dzTUEIIrI3B2vTu74bq0+RmtAmIIHlWmfjKLeuv5RLVqleOMqEK0SxndLS1hAx7FuPMeLNBkrfkWYa1u76+E1VKVnn8AW3jwz0+QIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi.alipaydev.com/gateway.do");
        alipayConfig.setAppId("2021000118637803");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo("70501111111S001111119");
        model.setTotalAmount("0.1");
        model.setSubject("大乐透");
        model.setProductCode("QUICK_WAP_PAY");
        model.setSellerId("2088102147948060");
        model.setQuitUrl("http://www.taobao.com/product/113714.html");
        request.setBizModel(model);
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}

