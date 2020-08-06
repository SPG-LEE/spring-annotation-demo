package com.spring.demo.controller.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.demo.bean.StudentBean;
import com.spring.demo.controller.FinishAbroadOrderRequest;
import com.spring.demo.controller.ListAbroadOrderRequest;
import com.spring.demo.entity.Class;
import com.spring.demo.entity.Grade;
import com.spring.demo.entity.Student;
import com.spring.demo.service.ClassService;
import com.spring.demo.service.GradeService;
import com.spring.demo.service.StudentService;
import com.spring.demo.util.DateUtil;
import okhttp3.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("demo/grades")
@Validated
@CrossOrigin(allowedHeaders = {"x-access-token"})
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;

    @GetMapping
    public List<Grade> findAll() {
        return gradeService.findAll();
    }

    @GetMapping("/{gradeId}")
    public Grade findAll(@PathVariable @Range(min = 1, max = 10000) long gradeId) {
        return gradeService.findById(gradeId);
    }

    @GetMapping("/{gradeId}/class/{classId}")
    public Class findAll(@PathVariable long gradeId, @PathVariable long classId) {
        return classService.findById(classId);
    }

    @GetMapping("/{gradeId}/class/{classId}/student/{studentId}")
    public Student findAll(@PathVariable long gradeId, @PathVariable long classId, @PathVariable long studentId) {
        return studentService.findById(studentId);
    }

    @GetMapping("/{gradeId}/class/{classId}/students")
    public List<StudentBean> findClassStudents(@PathVariable long gradeId, @PathVariable long classId) {
        System.out.println("0--");
        return studentService.findByClassId(classId);
    }

    @PutMapping("/test/{gradeId}")
    public void mergeTest(@PathVariable long gradeId, @RequestBody Grade grade) {
        gradeService.mergeTest(gradeId, grade);
    }

    @DeleteMapping("/{gradeId}")
    public void delete(@PathVariable long gradeId) {
        gradeService.delete(gradeService.findById(gradeId));
    }

    @DeleteMapping("/{gradeId}/class/{classId}")
    public void delete(@PathVariable long gradeId, @PathVariable long classId) {
        classService.delete(classService.findById(classId));
    }

    @DeleteMapping("/{gradeId}/class/{classId}/student/{studentId}")
    public void delete(@PathVariable long gradeId, @PathVariable long classId, @PathVariable long studentId) {
        studentService.delete(studentService.findById(studentId));
    }

    @GetMapping("/init")
    public void init() {
        String[] gradeName = {"一年级", "二年级", "三年级", "四年级", "五年级", "六年级"};
        List<Grade> grades = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            String[] className = {"一班", "二班", "三班", "四班"};
            Grade singleGrade = new Grade();
            singleGrade.setName(gradeName[i]);
            singleGrade.setNumber(gradeName[i]);
            singleGrade.setCreateDateTime(new Timestamp(singleGrade.getCreateDate().getTime()));
            gradeService.save(singleGrade);
            Set<Class> classes = new HashSet<>();
            for (int j = 0; j < 1; j++) {
                Class singleClass = new Class();
                singleClass.setName(className[j]);
                singleClass.setNumber(className[j]);
                singleClass.setGrade(singleGrade);
                classService.save(singleClass);
                Set<Student> students = new HashSet<>();
                for (int k = 0; k < 1000; k++) {
                    String[] firstName = {"张", "王", "李", "赵", "马", "刘"};
                    String[] lastName = {"大", "二", "三", "四", "五", "六"};
                    Student singleStudent = new Student();
                    singleStudent.setClasses(singleClass);
                    singleStudent.setName(firstName[new Random().nextInt(5)] + lastName[new Random().nextInt(5)]);
                    students.add(singleStudent);
                }
                singleClass.setStudents(students);
                classes.add(singleClass);
            }
            singleGrade.setClasses(classes);
            grades.add(singleGrade);
        }
        gradeService.saveAll(grades);
    }

    @GetMapping("/test/http")
    public String testHttp() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("shop", "{\"id\":15,\"createDate\":\"2018-06-08T05:15:22.000+0000\",\"updateDate\":\"2018-06-08T05:15:27.000+0000\",\"entityStatus\":\"ENABLE\",\"platform\":\"AMAZON\"," +
                "\"sellerId\":\"A3IMRMPFZSGWEC\",\"location\":\"A13V1IB3VIYZZH\",\"nickName\":\"AM-shiyi-fr\",\"account\":{\"id\":7,\"createDate\":\"2017-12-10T22:44:24.000+0000\"," +
                "\"updateDate\":\"2017-12-10T22:44:24.000+0000\",\"entityStatus\":\"ENABLE\",\"account\":\"ShiyiUp授权数据勿删勿选\",\"bucketName\":\"qwgl6\",\"newAuthorize\":false,\"location\":\"EU\"," +
                "\"locationNickName\":\"拾意欧洲\",\"accountNickName\":\"拾意\",\"accessMwsToken\":null,\"sellerId\":\"A3IMRMPFZSGWEC\",\"fbaInventoryName\":\"备货仓/义乌（浦江）备货仓\",\"fbaInventoryId\":92," +
                "\"deliverAddressId\":97,\"deliverAddress\":\"Shiyi拾意-FBA专用\",\"sort\":1},\"hasPullOrder\":true,\"fbaInventoryName\":\"备货仓/义乌（浦江）备货仓\",\"fbaInventoryId\":92,\"deliverAddressId\":97," +
                "\"deliverAddress\":\"Shiyi拾意-FBA专用\",\"countryCode\":\"FR\",\"accessMwsToken\":null,\"authorizationAccount\":{\"id\":1,\"createDate\":\"2019-06-18T06:26:10.000+0000\"," +
                "\"updateDate\":\"2019-06-18T06:26:17.000+0000\",\"entityStatus\":\"ENABLE\",\"account\":\"ShiyiUp-EU授权数据勿删勿选\",\"accessKeyId\":\"AKIAJJFGBGGAFK2LUUJQ\"," +
                "\"accessSecretKey\":\"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\",\"url\":null,\"location\":\"EU\"},\"currency\":\"EUR\",\"sort\":2}");
        params.put("amazonId", "408-3890465-0295556");
        String s = httpPostMethod("http://samo.aiqier.org/orders/listOrderItems", params);
        return s;
    }

    public static void main(String[] args) throws Exception {
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(null, null, null);
//
//        SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
//        SSLSocket socket = (SSLSocket) factory.createSocket();
//
//        String[] protocols = socket.getSupportedProtocols();
//
//        System.out.println("Supported Protocols: " + protocols.length);
//        for (int i = 0; i < protocols.length; i++) {
//            System.out.println(" " + protocols[i]);
//        }
//
//        protocols = socket.getEnabledProtocols();
//
//        System.out.println("Enabled Protocols: " + protocols.length);
//        for (int i = 0; i < protocols.length; i++) {
//            System.out.println(" " + protocols[i]);
//        }

        Date startDate = DateUtil.parseStr2Date("2020-01-15");
        Date nextDate = DateUtil.addDaysToDate(startDate,7);
        Date end = DateUtil.parseStr2Date("2020-01-31");
        while (DateUtil.daysBetween(nextDate, DateUtil.getNextDayStartByStart(end)) >= 0) {
            String curDate = DateUtil.dateFormat(startDate, "yyyy-MM-dd HH:mm:ss");
            String curNextDate = DateUtil.dateFormat(nextDate, "yyyy-MM-dd HH:mm:ss");
            startDate = new Date(nextDate.getTime());
            nextDate = DateUtil.addDaysToDate(nextDate,7);
            System.out.println(curDate + "********" + curNextDate);
        }
    }

    @GetMapping("/test/http1")
    public String testHttp1(@RequestParam long shopId) throws IOException, InterruptedException {
        Date startDate = DateUtil.parseStr2Date("2019-01-01");
        Date nextDate = DateUtil.addDaysToDate(startDate,7);
        Date end = DateUtil.parseStr2Date("2019-05-31");
        while (DateUtil.daysBetween(nextDate, DateUtil.getNextDayStartByStart(end)) >= 0) {
            String curDate = DateUtil.dateFormat(startDate, "yyyy-MM-dd HH:mm:ss");
            String curNextDate = DateUtil.dateFormat(nextDate, "yyyy-MM-dd HH:mm:ss");

            Map<String, String> params = new HashMap<>();
            params.put("shopId", shopId + "");
            params.put("from", curDate);
            params.put("to", curNextDate);

            params.put("isPassReport", "true");
            Map<String, String> head = new HashMap<>();
            head.put("x-access-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaWFubG9uZy5saSwxNTk2NjkyMjIyMDM2In0.h16mEWt9NdVf-KE2qXVIxUTGlWwxVau5pJy60i5p31XDLkeSTz4J_HUURMbe8cieXxRPdbma4pHH14VlshgCqQ");
            String s = "";
            try {

                s = httpPutMethod("https://www.angelerp.com/rest/amazon/orders/pull", params, head);
            } catch (Exception e) {

            }

            if (!s.contains("操作成功")) {
                System.out.println(s);
                System.out.println(curDate + "********" + curNextDate);
            }
            Thread.sleep(2000);
            startDate = new Date(nextDate.getTime());
            nextDate = DateUtil.addDaysToDate(nextDate,7);
        }
        return null;
    }

    @GetMapping("/test/http2")
    public String testHttp2(@RequestParam long shopId) throws IOException, InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("2020-04-22 00:00:00----------2020-04-23 00:00:00");
        Map<String, String> dateMap = new LinkedHashMap<>();
        for (String s : list) {
            String[] split = s.split("----------");
            dateMap.put(split[0], split[1]);
        }
        for (String date : dateMap.keySet()) {
            String curDate = date;
            String curNextDate = dateMap.get(date);

            Map<String, String> params = new HashMap<>();
            params.put("shopId", shopId + "");
            params.put("from", curDate);
            params.put("to", curNextDate);

            params.put("isPassReport", "true");
            Map<String, String> head = new HashMap<>();
            head.put("x-access-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaWFubG9uZy5saSwxNTk2NjkyMjIyMDM2In0.h16mEWt9NdVf-KE2qXVIxUTGlWwxVau5pJy60i5p31XDLkeSTz4J_HUURMbe8cieXxRPdbma4pHH14VlshgCqQ");
            String s = "";
            try {

                s = httpPutMethod("http://erp.aiqier.org:8921/amazon/orders/pull", params, head);
            } catch (Exception e) {

            }

            if (!s.contains("操作成功")) {
                System.out.println(s);
                System.out.println(curDate + "********" + curNextDate);
            }
            Thread.sleep(1000);
        }
        return null;
    }

    @GetMapping("/test/http3")
    public String testHttp3() throws IOException, InterruptedException {
        int pageSize = 0;
        List<Long> orderIdList = getNextIdList(pageSize);
        do {
            List<FinishAbroadOrderRequest> body = orderIdList.stream().map(map -> FinishAbroadOrderRequest.builder().id(map).build()).collect(Collectors.toList());
            Map<String, String> toFinishHead = new HashMap<>();
            toFinishHead.put("x-access-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaWFubG9uZy5saSwxNTkzMzEzOTcwNzgxIn0.J8WLUT1loWqQxBHXT-qswCG8DF5sQnZHkN7Dq4dEnC2p7Dp2QejUW3R9ygYB9sdPs0rQc_OO76DbNRDdgf9OdA");
            String result = "";
            try {

                result = httpPutMethod("http://www.angelerp.com:8971/orders/abroad/toFinish", JSON.toJSONString(body), toFinishHead);
            } catch (Exception e) {

            }

            if (!result.contains("操作成功")) {
                JSONObject toFinishResult = JSONObject.parseObject(result);
                JSONArray errorData = toFinishResult.getJSONArray("data");
                Map<String, String> reasonMap = errorData.stream().collect(Collectors.toMap(key -> ((JSONObject) key).getString("number"), value -> ((JSONObject) value).getString("reason")));
                reasonMap.keySet().forEach(reason->{
                    System.out.println(reason+": "+reasonMap.get(reason));
                });
                System.out.println("***********errorPage:" + pageSize + "************8");
            }
            orderIdList = getNextIdList(pageSize);
//            pageSize++;
            Thread.sleep(1000);
        } while (!orderIdList.isEmpty());
        return null;
    }

    private List<Long> getNextIdList(int pageSize) {
        List<String> shopNames = new ArrayList<>();
        shopNames.add("AM-shiyi-us");
        shopNames.add("AM-shiyi-ca");
        shopNames.add("AM-shiyi-MX");
        shopNames.add("AM-shiyi-uk");
        shopNames.add("AM-shiyi-de");
        shopNames.add("AM-shiyi-fr");
        shopNames.add("AM-shiyi-es");
        shopNames.add("AM-shiyi-it");
        shopNames.add("Yiru-US");
        shopNames.add("Yiru-CA");
        shopNames.add("Yiru-MX");
        shopNames.add("Yiru-UK");
        shopNames.add("Yiru-De");
        shopNames.add("Yiru-fr");
        shopNames.add("Yiru-es");
        shopNames.add("Yiru-it");
        shopNames.add("Feynman-US");
        shopNames.add("Feynman-CA");
        shopNames.add("Feynman-MX");
        shopNames.add("Feynman-UK");
        shopNames.add("Feynman-DE");
        shopNames.add("Feynman-FR");
        shopNames.add("Feynman-ES");
        shopNames.add("Feynman-IT");
        shopNames.add("Feynman-JP");
        shopNames.add("US_Uheng");
        shopNames.add("CA_Uheng");
        shopNames.add("MX_Uheng");
        shopNames.add("uk_Uheng");
        shopNames.add("de_Uheng");
        shopNames.add("fr_Uheng");
        shopNames.add("es_Uheng");
        shopNames.add("it_Uheng");
        shopNames.add("xuanmuque-US");
        shopNames.add("xuanmuque-CA");
        shopNames.add("xuanmuque-MX");
        shopNames.add("US-JMsDream");
        shopNames.add("CA-JMsDream");
        shopNames.add("MX-JMsDream");
        shopNames.add("Jiyu-US");
        shopNames.add("Jiyu-CA");
        shopNames.add("Jiyu-MX");
        shopNames.add("Jiyu-UK");
        shopNames.add("Jiyu-DE");
        shopNames.add("Jiyu-FR");
        shopNames.add("Jiyu-ES");
        shopNames.add("Jiyu-IT");
        shopNames.add("Jiyu-JP");
        shopNames.add("Qiunuo-US");
        shopNames.add("Qiunuo-UK");
        shopNames.add("Qiunuo-DE");
        shopNames.add("Qiunuo-FR");
        shopNames.add("Qiunuo-ES");
        shopNames.add("Qiunuo-IT");
        shopNames.add("Qiunuo-JP");
        shopNames.add("Fantany_Direct_US");
        shopNames.add("Fantany_Direct_CA");
        shopNames.add("Fantany_Direct_MX");
        shopNames.add("Fantany-UK");
        shopNames.add("Fantany-DE");
        shopNames.add("Fantany-FR");
        shopNames.add("Fantany-ES");
        shopNames.add("Fantany-IT");
        shopNames.add("taozhiyaoyao-US");
        shopNames.add("taozhiyaoyao-CA");
        shopNames.add("taozhiyaoyao-MX");
        shopNames.add("taozhiyaoyao-uk");
        shopNames.add("taozhiyaoyao-it");
        shopNames.add("taozhiyaoyao-de");
        shopNames.add("taozhiyaoyao-fr");
        shopNames.add("taozhiyaoyao-es");
        shopNames.add("UK-Aiqier");
        shopNames.add("ElementLights-US");
        shopNames.add("ElementLights-CA");
        shopNames.add("ElementLights-MX");
        shopNames.add("xiaobang-US");
        shopNames.add("xiaobang-CA");
        shopNames.add("xiaobang-MX");
        ListAbroadOrderRequest body = ListAbroadOrderRequest.builder()
                .orderByField("+paymentTime")
                .orderOutStatus("ABROAD")
                .orderStatus("DEAL")
                .pageIndex(pageSize)
                .pageSize(50)
                .shopNames(shopNames)
                .build();
        Map<String, String> head = new HashMap<>();
        head.put("x-access-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaWFubG9uZy5saSwxNTkzMzEzOTcwNzgxIn0.J8WLUT1loWqQxBHXT-qswCG8DF5sQnZHkN7Dq4dEnC2p7Dp2QejUW3R9ygYB9sdPs0rQc_OO76DbNRDdgf9OdA");
        String s = "";
        try {

            s = httpPostMethod("http://www.angelerp.com:8971/orders/abroad/list", JSON.toJSONString(body), head);
        } catch (Exception e) {

        }
        JSONObject orderListResult = JSONObject.parseObject(s);
        Boolean hasSuccess = orderListResult.getBoolean("success");
        if (!hasSuccess) {
            return new ArrayList<>();
        }
        JSONObject orderListData = orderListResult.getJSONObject("data");
        JSONArray orderList = orderListData.getJSONArray("data");
        return orderList.stream().map(map -> ((JSONObject) map).getLong("id")).collect(Collectors.toList());
    }

    public static String httpPutMethod(String url, String body, Map<String, String> head)
            throws IOException {
        Headers headers = Headers.of(head);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS).build();

//        FormBody.Builder formBody = new FormBody.Builder();
//        params.keySet().stream().forEach(key -> {
//            formBody.add(key, params.get(key));
//        });
        MediaType mediaType = MediaType
                .parse(headers.get("Content-Type") == null ? "application/json"
                        : headers.get("Content-Type"));
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .put(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String httpPutMethod(String url, Map<String, String> params, Map<String, String> head)
            throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.MINUTES).build();

        Headers headers = Headers.of(head);
        FormBody.Builder formBody = new FormBody.Builder();
        params.keySet().stream().forEach(key -> {
            formBody.add(key, params.get(key));
        });
//        url = url + "?1=1";
//        for (String key : params.keySet()) {
//            url += "&";
//            url += key + "=" + params.get(key);
//        }
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .put(formBody.build())
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String httpPostMethod(String url, String body, Map<String, String> head)
            throws IOException {
        Headers headers = Headers.of(head);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS).build();

//        FormBody.Builder formBody = new FormBody.Builder();
//        params.keySet().stream().forEach(key -> {
//            formBody.add(key, params.get(key));
//        });
        MediaType mediaType = MediaType
                .parse(headers.get("Content-Type") == null ? "application/json"
                        : headers.get("Content-Type"));
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String httpPostMethod(String url, Map<String, String> params)
            throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();

        FormBody.Builder formBody = new FormBody.Builder();
        params.keySet().stream().forEach(key -> {
            formBody.add(key, params.get(key));
        });
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-type", "application/text; charset=utf-8")
                .post(formBody.build())
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
