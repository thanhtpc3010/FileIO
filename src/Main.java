import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

                String sysPath = System.getProperty("user.dir");
                String fileInPath = sysPath.replace("/", "\\") + "/data/orderDetail.in.txt";
                String fileOutPath = sysPath.replace("/", "\\") + "/data/OrderCustomer.out.txt";

                Map<Integer, Product> productMap = new HashMap<>();
                Map<Integer, Order> orderMap = new HashMap<>();
                List<OrderDetail> orderDetails = new ArrayList<>();


                productMap.put(121, new Product(121, "Product A", 120, 10));
                productMap.put(122, new Product(122, "Product B", 125, 5));
                productMap.put(125, new Product(125, "Product C", 105, 15));

                orderMap.put(1, new Order(1, 1, "2023-03-15 09:30:15"));
                orderMap.put(2, new Order(2, 2, "2023-06-09 19:30:25"));

                // READ
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInPath))) {
                    String lineData;
                    while ((lineData = bufferedReader.readLine()) != null) {
                        if (!lineData.isEmpty()) {
                            String[] arData = lineData.split(";");
                            int id = Integer.parseInt(arData[0].trim());
                            int orderId = Integer.parseInt(arData[1].trim());
                            int productId = Integer.parseInt(arData[2].trim());
                            int quantity = Integer.parseInt(arData[3].trim());
                            double price = Double.parseDouble(arData[4].trim());

                            Order order = orderMap.get(orderId);
                            Product product = productMap.get(productId);

                            if (order != null && product != null) {
                                OrderDetail orderDetail = new OrderDetail(id, order, product, quantity, price);
                                orderDetails.add(orderDetail);
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }

                int targetCustomerId = 1; // ID của khách hàng cần lấy hóa đơn
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOutPath))) {
                    for (OrderDetail detail : orderDetails) {
                        if (detail.getOrder().getCustomer().getId() == targetCustomerId) {
                            String invoiceLine = detail.toString();
                            System.out.println(invoiceLine);  // Hiển thị hóa đơn
                            bufferedWriter.write(invoiceLine);
                            bufferedWriter.newLine();
                        }
                    }
                    System.out.println("Hóa đơn của khách hàng đã được lưu vào file " + fileOutPath);
                } catch (IOException e) {
                    System.out.println("Error writing file: " + e.getMessage());
                }
            }
        }
