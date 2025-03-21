package com.company.projectmanagementdata.view.order;

import com.company.projectmanagementdata.entity.Order;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "orders/:id", layout = MainView.class)
@ViewController(id = "Order_.detail")
@ViewDescriptor(path = "order-detail-view.xml")
@EditedEntityContainer("orderDc")
public class OrderDetailView extends StandardDetailView<Order> {
}