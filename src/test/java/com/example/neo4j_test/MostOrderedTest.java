package com.example.neo4j_test;

import com.sprinklr.neo4j.Neo4jWrapper;

import com.example.neo4j_test.customQueryClasses.ProductDetail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MostOrderedTest {

    @Autowired
    private Neo4jWrapper neo4jWrapper;

    @Test
    @Transactional
    public void aggregateOrderProduct() {
        String query = """
                MATCH (p:Product {productID: "1"})<-[:ORDERS]-(o:Order)-[:ORDERS]->(other:Product)
                RETURN other.productID AS productID, other.productName AS productName, COUNT(other) AS count
                ORDER BY count DESC
                LIMIT 1;
                """;

        List<ProductDetail> ALFKIOrders = neo4jWrapper.executeQuery(query, ProductDetail.class);

        for (ProductDetail productDetail: ALFKIOrders) {
            assertThat(productDetail.getProductID()).isNotNull();
            assertThat(productDetail.getCount()).isNotZero();
        }
    }

//        db.orders.aggregate([
//        { $match: { "OrderDetails.ProductID": 1 } },
//        { $unwind: "$OrderDetails" },
//        { $group: {
//            _id: "$OrderID",
//                    products: { $push: "$OrderDetails.ProductID" }
//        }
//        },
//        { $unwind: "$products" },
//        { $match: { products: { $ne: 1 } } },
//        { $group: {
//            _id: "$products",
//                    count: { $sum: 1 }
//        }
//        },
//        { $sort: { count: -1 } },
//        { $limit: 10 },
//        { $lookup: {
//            from: "products",
//                    localField: "_id",
//                    foreignField: "ProductID",
//                    as: "productInfo"
//        }
//        },
//        { $unwind: "$productInfo" },
//        { $project: {
//            _id: 0,
//                    productID: "$_id",
//                    productName: "$productInfo.ProductName",
//                    count: 1
//        }
//        }
//    ]);

}
