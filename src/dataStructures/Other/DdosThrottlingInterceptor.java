/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * /foo.js sourceURl/domain : IP/dn.com latency
 * /product.js ... IP/dn.com
 *
 * API: List<IP> listBots()
 *
 * Log format : "clientId=1 apiName=b ip=127.0.0.1"
 * @author ashish gupta (akonda@expedia.com)
 */
public class DdosThrottlingInterceptor {

    private Map<LoggerMapKey, Map<String, Long>> loggerMap;

    private List<String> listBots(List<String> logList, int limit,
                                            List<ClientLimiter> lsClient) {
        List<String> ips = new ArrayList<>();

        loggerMap = new HashMap<>();

        if (logList.isEmpty() || limit < 1 || lsClient.isEmpty())
            return ips;

        for (String ls : logList) {
            Log log = parse(ls);
            LoggerMapKey mapKey = generateKey(log);

            if (loggerMap.containsKey(mapKey)) {
                Map<String, Long> curVal = loggerMap.get(mapKey);
                if( curVal.containsKey(log.ip)) {
                    long count = curVal.get(log.ip);
                    curVal.put(log.ip, count+1);
                    loggerMap.put(mapKey, curVal);
                }
            } else {
                Map<String, Long> ipCount = new HashMap<>();
                ipCount.put(log.ip, (long) 1);
                loggerMap.put(mapKey, ipCount);
            }
        }

        return ips;
    }

    private LoggerMapKey generateKey(Log log) {
        return new LoggerMapKey(log.clientId, log.apiName, log.domain);
    }

    private Log parse(String ls) {
        Log log = new Log();

        String[] split = ls.split("\\s+");
        for (String s : split) {
            String[] spLs = s.split("=");
            if (spLs[0].equals("clientId")) {
                log.clientId = Integer.valueOf(spLs[1]);
            }
            else if (spLs[0].equals("apiName"))
                log.apiName = spLs[1];
            else if (spLs[0].equals("ip"))
                log.ip = spLs[1];
            else
                log.domain = spLs[1];
        }
        return log;
    }


    class LoggerMapKey {
        int clientId;
        String apiName;
        String domain;

        LoggerMapKey(int clientId, String apiName, String domain){
            this.clientId = clientId;
            this.apiName = apiName;
            this.domain = domain;
        }

        @Override
        public boolean equals(Object o) {

            if (o == this) return true;
            if (!(o instanceof LoggerMapKey)) {
                return false;
            }
            LoggerMapKey user = (LoggerMapKey) o;
            return clientId == user.clientId &&
                    Objects.equals(apiName, user.apiName) &&
                    Objects.equals(domain, user.domain);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clientId, apiName,
                                domain);
        }
    }

    class Log {
        int clientId;
        String apiName;
        String ip;
        String domain ="";
    }

    class ClientLimiter {
        int clientId;
        String apiName;
        int maxLimit;

        public ClientLimiter(int clientId, String apiName, int maxLimit) {
            this.clientId = clientId;
            this.apiName = apiName;
            this.maxLimit = maxLimit;
        }
    }

    public static void main(String args[] ) throws Exception {

        DdosThrottlingInterceptor logTracer = new DdosThrottlingInterceptor();

        List<String> ls = new ArrayList<>();
        ls.add("clientId=1 apiName=a ip=127.0.0.1 domain=foo.com");
        ls.add("clientId=1 apiName=a ip=127.0.0.1 domain=foo.com");
        ls.add("clientId=2 apiName=a ip=127.0.0.1 domain=foo.com");
        ls.add("clientId=1 apiName=b ip=127.0.0.1 domain=bar.com");

        ClientLimiter clientLimiter1 = new DdosThrottlingInterceptor().
                                            new ClientLimiter(1,"a",1);

        ClientLimiter clientLimiter2 = new DdosThrottlingInterceptor().
                new ClientLimiter(1,"a",1);

        List<ClientLimiter> lsClient = new ArrayList<>();
        lsClient.add(clientLimiter1);
        lsClient.add(clientLimiter2);

        System.out.println(logTracer.listBots(ls, 2, lsClient));
    }

}

