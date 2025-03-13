package com.company.projectmanagementdata.view.customseach;


import com.company.projectmanagementdata.entity.Customer;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "custom-seach-view", layout = MainView.class)
@ViewController(id = "CustomSeachView")
@ViewDescriptor(path = "custom-seach-view.xml")
public class CustomSeachView extends StandardView {
    private static final Logger log = LoggerFactory.getLogger(CustomSeachView.class);
    @ViewComponent
    private CollectionContainer<Customer> customersDc;

    @Subscribe
    public void onInit(final InitEvent event) {
        log.info("Init event - loaded customers: " + customersDc.getItems().size());
    }
}