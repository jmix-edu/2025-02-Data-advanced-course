package com.company.projectmanagementdata.view.roadmap;

import com.company.projectmanagementdata.entity.Roadmap;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "roadmaps", layout = MainView.class)
@ViewController(id = "Roadmap.list")
@ViewDescriptor(path = "roadmap-list-view.xml")
@LookupComponent("roadmapsDataGrid")
@DialogMode(width = "64em")
public class RoadmapListView extends StandardListView<Roadmap> {
}