sap.ui.jsview("splitappdemo.view.Intro", {

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed.
	* Since the Controller is given to this method, its event handlers can be attached right away.
	* @memberOf splitappdemo.Intro
	*/
	createContent : function(oController) {
 		return new sap.m.Page({
			title: "Dashboard",
			content: [
			      new sap.ui.commons.TextView({
			    	  text: "Welcome to the custom monitoring dashboard. Please select an application from the left panel to see the details.",
			    	  design: sap.ui.commons.TextViewDesign.H5
			      })
			]
		});
	}

});