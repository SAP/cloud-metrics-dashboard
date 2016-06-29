sap.ui.localResources("splitappdemo");

var oSplitApp = new sap.m.SplitApp("appId", {});

var oIntroPage = sap.ui.view({
	id : "introId",
	viewName : "splitappdemo.view.Intro",
	type : sap.ui.core.mvc.ViewType.JS
})

var oDetailsPage = sap.ui.view({
	id : "detailsId",
	viewName : "splitappdemo.view.Details",
	type : sap.ui.core.mvc.ViewType.JS
})

var oMasterPage = sap.ui.view({
	id : "masterId",
	viewName : "splitappdemo.view.Master",
	type : sap.ui.core.mvc.ViewType.JS
});

oSplitApp.addDetailPage(oIntroPage).addDetailPage(oDetailsPage);

oSplitApp.addMasterPage(oMasterPage);

oSplitApp.setInitialDetail("introId");
oSplitApp.setInitialMaster("masterId");

oSplitApp.placeAt("content");

oSplitApp.setDefaultTransitionNameDetail("slide");
oSplitApp.setMode("ShowHideMode");
