package com.company.projectmanagementdata.view.customersubstringsearch;


import com.company.projectmanagementdata.entity.Customer;
import com.company.projectmanagementdata.view.main.MainView;
import com.google.common.base.Strings;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

@Route(value = "customer-substring-search-view", layout = MainView.class)
@ViewController(id = "CustomerSubstringSearchView")
@ViewDescriptor(path = "customer-substring-search-view.xml")
public class CustomerSubstringSearchView extends StandardView {
//
//    @ViewComponent
//    private CollectionLoader<Customer> customersDl;
//
//    @Subscribe("firstNameField")
//    public void onFirstNameFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedTextField<String>, String> event) {
//        customersDl.setParameter("name",
//                Strings.isNullOrEmpty(event.getValue())
//                        ? null
//                        : "(?i)%" + event.getValue() + "%"
//        );
//
//        customersDl.load();
//    }
}