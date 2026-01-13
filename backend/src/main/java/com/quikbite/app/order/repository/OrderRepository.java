package com.quikbite.app.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quikbite.app.auth_users.model.User;
import com.quikbite.app.enums.OrderStatus;
import com.quikbite.app.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * 
     * @param status
     * @param pageable
     * @return
     * how it works internally is that when we call this method, its takes order status and pagable interface as input(pagable interface contains page number, size and sorting info)
     * then it generates a query like "SELECT * FROM orders WHERE status = ? LIMIT ? OFFSET ?" and executes it against the database
     * finally it maps the result set to a Page<Order> object and returns it. so the page object returned will be in below format,
     * {
     *      content: [list of orders for the requested page],
     *      pageable: {page number, size, sort info},
     *      totalElements: total number of orders with the given status,
     *      totalPages: total number of pages available,
     *      last: boolean indicating if this is the last page,
     *      first: boolean indicating if this is the first page,
     *      numberOfElements: number of orders in the current page
     * }
     * and input pagable will be in below format,
     * {
     *      pageNumber: requested page number, [ page number starts from 0 ]
     *      pageSize: requested page size,[ page size means how many records we want in a single page]
     *      sort: sorting info [ like sort by orderDate desc or totalAmount asc ]
     * }
     * below was an example for input pagable,
     * {
     *     pageNumber: 0,
     *     pageSize: 10,
     *     sort: "orderDate desc"
     * }
     * and the output Page<Order> will be in below format,
     * {
     *     content: [list of first 10 orders with the given status sorted by order
     *   date desc],
     *    pageable: {pageNumber: 0, pageSize: 10, sort: "orderDate desc"},
     *    totalElements: 57,
     *    totalPages: 6,
     *    last: false,
     *    first: true,
     *    numberOfElements: 10
     * }
     * ---------------------------------------------
     * internally 
     */
    Page<Order> findOrderByStatus(OrderStatus status, Pageable pageable);
    List<Order> findByUserOrderByOrderDateDesc(User user);

    @Query("SELECT COUNT(DISTINCT o.user) FROM Order o")
    long countDistinctUsers();
}
