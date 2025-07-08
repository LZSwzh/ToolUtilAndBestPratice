//package com.best.pratice.httpClient.mockserver;
//
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import com.sun.net.httpserver.HttpServer;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.InetSocketAddress;
//
//public class MockIOExceptionServer {
//    public static void main(String[] args) {
//        HttpServer httpServer = null;
//        //注意backlog制定了链接请求队列的最大长度
//        try {
//            httpServer = HttpServer.create(new InetSocketAddress(9999), 0);
//            // 注册异常模拟处理器
//            httpServer.createContext("/simulate-io", new IOExceptionHandler());
//
//            // 注册正常响应端点（用于对比）
//            httpServer.createContext("/normal", exchange -> {
//                httpServer.sendResponse(exchange, 200, "Normal Response");
//            });
//
//            httpServer.start();
//            System.out.println("Mock Server started at http://localhost:" + port);
//            System.out.println("Test endpoints:");
//            System.out.println("  - /simulate-io?type=connect  模拟连接超时");
//            System.out.println("  - /simulate-io?type=socket   模拟读取超时");
//            System.out.println("  - /simulate-io?type=reset    模拟连接重置");
//            System.out.println("  - /simulate-io?type=close    模拟连接关闭");
//            System.out.println("  - /normal                    正常响应");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            httpServer.stop(0);
//        }
//    }
//
//    static class IOExceptionHandler implements HttpHandler {
//        @Override
//        public void handle(HttpExchange exchange) throws IOException {
//            int count = requestCounter.incrementAndGet();
//            String type = exchange.getRequestURI().getQuery();
//            type = type != null ? type.split("=")[1] : "reset";
//
//            System.out.println("\n[Request #" + count + "] Received " + type.toUpperCase() + " simulation request");
//
//            switch (type.toLowerCase()) {
//                case "connect":
//                    simulateConnectTimeout(exchange);
//                    break;
//                case "socket":
//                    simulateSocketTimeout(exchange);
//                    break;
//                case "reset":
//                    simulateConnectionReset(exchange);
//                    break;
//                case "close":
//                    simulateConnectionClose(exchange);
//                    break;
//                default:
//                    sendResponse(exchange, 400, "Invalid simulation type");
//            }
//        }
//
//        // 模拟连接超时 (ConnectTimeoutException)
//        private void simulateConnectTimeout(HttpExchange exchange) {
//            System.out.println("Simulating CONNECT_TIMEOUT - Not accepting connection");
//            // 故意不建立连接，让客户端超时
//        }
//
//        // 模拟读取超时 (SocketTimeoutException)
//        private void simulateSocketTimeout(HttpExchange exchange) throws IOException {
//            System.out.println("Simulating SOCKET_TIMEOUT - Accepting connection but not sending response");
//            exchange.sendResponseHeaders(200, 0);
//            // 不关闭流，让客户端等待直到超时
//        }
//
//        // 模拟连接重置 (SocketException: Connection reset)
//        private void simulateConnectionReset(HttpExchange exchange) {
//            System.out.println("Simulating CONNECTION_RESET");
//            // 强制关闭底层Socket触发连接重置
//            exchange.getRequestBody().close();
//            try {
//                exchange.getResponseBody().close();
//            } catch (Exception ignored) {}
//        }
//
//        // 模拟连接关闭 (ConnectionClosedException)
//        private void simulateConnectionClose(HttpExchange exchange) throws IOException {
//            System.out.println("Simulating CONNECTION_CLOSED");
//            exchange.sendResponseHeaders(200, "Response will be closed".getBytes().length);
//            try (OutputStream os = exchange.getResponseBody()) {
//                os.write("Partial response...".getBytes());
//                // 中途关闭连接
//                throw new IOException("Server closed connection intentionally");
//            }
//        }
//
//        private void sendResponse(HttpExchange exchange, int statusCode, String body) throws IOException {
//            exchange.sendResponseHeaders(statusCode, body.length());
//            try (OutputStream os = exchange.getResponseBody()) {
//                os.write(body.getBytes());
//            }
//            System.out.println("Sent normal response: " + statusCode);
//        }
//    }
//}
