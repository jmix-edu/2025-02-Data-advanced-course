package com.company.projectmanagementdata.view.customseach;


import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "custom-seach-view", layout = MainView.class)
@ViewController(id = "CustomSeachView")
@ViewDescriptor(path = "custom-seach-view.xml")
public class CustomSeachView extends StandardView {
}