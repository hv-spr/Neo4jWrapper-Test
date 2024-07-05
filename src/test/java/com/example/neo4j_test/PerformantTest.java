package com.example.neo4j_test;

import com.sprinklr.neo4j.Neo4jWrapper;

import com.example.neo4j_test.customQueryClasses.OrderDetail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PerformantTest {

    @Autowired
    private Neo4jWrapper neo4jWrapper;

    @Test
    @Transactional
    public void complexQueryTest() {
        String query = """
        MATCH (c:Customer {customerID: "ALFKI"})-[:PURCHASED]->(o:Order)-[:ORDERS]->(p:Product)
        RETURN c as customerEntity, c.customerID AS customerID, c.contactName AS contactName, o.orderID AS orderID, o.orderDate AS orderDate, p.productName AS productName""";

        List<OrderDetail> ALFKIOrders = neo4jWrapper.executeQuery(query, OrderDetail.class);

        for (OrderDetail orderDetail: ALFKIOrders) {
            assertThat(orderDetail.getCustomerEntity().getCustomerID()).isEqualTo(orderDetail.getCustomerID());
        }
    }

//        db.customers.aggregate([
//        { $match: { CustomerID: "ALFKI" } },
//        { $lookup: {
//            from: "orders",
//                    localField: "CustomerID",
//                    foreignField: "CustomerID",
//                    as: "orders"
//        }
//        },
//        { $unwind: "$orders" },
//        { $unwind: "$orders.OrderDetails" },
//        { $lookup: {
//            from: "products",
//                    localField: "orders.OrderDetails.ProductID",
//                    foreignField: "ProductID",
//                    as: "product_info"
//        }
//        },
//        { $unwind: "$product_info" },
//        { $project: {
//            _id: 0,
//                    CustomerID: 1,
//                    ContactName: 1,
//                    OrderID: "$orders.OrderID",
//                    OrderDate: "$orders.OrderDate",
//                    ProductName: "$product_info.ProductName",
//                    Quantity: "$orders.OrderDetails.Quantity"
//        }
//        }
//    ]);

}
