sap.ui.controller("splitappdemo.controller.Master", {

	/**
	 * Called when a controller is instantiated and its View controls (if
	 * available) are already created. Can be used to modify the View before it
	 * is displayed, to bind event handlers and do other one-time
	 * initialization.
	 *
	 * @memberOf splitappdemo.Master
	 */
	onInit : function() {
		var oModel = new sap.ui.model.json.JSONModel("./applications");
		sap.ui.getCore().setModel(oModel, 'data');
	},

	onItemSelected : function() {

		var app = sap.ui.getCore().byId("appId");
		var list = sap.ui.getCore().byId("listId");

		var sItem = list.getSelectedItem();
		var sPath = sItem.oBindingContexts.data.sPath;

		var item = sap.ui.getCore().getModel('data').getProperty(sPath);

		sap.ui.getCore().getModel('item').setData(item);

		app.toDetail('detailsId', 'slide')
	}

});