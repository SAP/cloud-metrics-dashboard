sap.ui.jsview("splitappdemo.view.Details", {

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed.
	* Since the Controller is given to this method, its event handlers can be attached right away.
	* @memberOf splitappdemo.Details
	*/
	createContent : function(oController) {

		function rowCounter(rows) {
			if (rows) {
				return rows.length;
			}
			return 1;
		}

		var oLayout = new sap.ui.commons.layout.MatrixLayout({
			id: "matrixId",
			layoutFixed: true,
			columns: 1,
			width: '100%'
		});

		var oTable = new sap.ui.table.Table({ // create Table UI
			selectionMode : sap.ui.table.SelectionMode.Single,
			title: "Processes",
			editable: false,
			selectionBehavior : sap.ui.table.SelectionBehavior.Row,
			visibleRowCount: {
				path: "/processes",
				formatter: rowCounter
			},
			minAutoRowCount: 1,
			noDataText: "No processes are found for this application.",
			columns : [
				{label: "Process Name", template: "process"},
				{label: "Process State", template: "state"}

			]
		});

		var oItemModel = new sap.ui.model.json.JSONModel();
		sap.ui.getCore().setModel(oItemModel,'item');
		oTable.setModel(oItemModel); // set model to Table
		oTable.bindRows("/processes");

		oTable.bindProperty("visibleRowCount", "/processes", function(processes) {
			oTable.clearSelection();
			return rowCounter(processes);
		});

		oLayout.createRow(oTable);

		var oMetricsTable = new sap.ui.table.Table({
			title: "Metrics",
			selectionBehavior : sap.ui.table.SelectionBehavior.Row,
			selectionMode : sap.ui.table.SelectionMode.Single,
			editable: false,
			noDataText: "Select a process to see its metrics.",
			visibleRowCount: {
				path: "metrics",
				formatter: rowCounter
			},
			minAutoRowCount: 1,
			columns : [
				{label: "Metric Name", template: "name"},
				{label: "Metric State", template: "state"}
			]
		});
		oMetricsTable.addColumn(new sap.ui.table.ColumnHeader({
			label: new sap.ui.commons.Label({text: "Metric Value"}),
			template: new sap.ui.commons.TextField({
				value: {
					parts : ["value", "unit"],
					formatter : function(value, unit) {
						return (unit === undefined) ? value : value + " " + unit;
					}
				},
				editable : false
			})
		}));

		var oThresholdCellMatrixLayout = new sap.ui.commons.layout.MatrixLayout({
			columns : 2
		});

		var oWarningThresholdCell = new sap.ui.commons.layout.MatrixLayoutCell({
			colSpan: 1 });

		oWarningThresholdCell.addContent(new sap.ui.commons.TextField({
			editable : false,
			value : {
				parts : ["warningThreshold"],
				formatter : function(warningThreshold) {
					if(warningThreshold == 0) {
						return "";
					}

					return "W > " + warningThreshold;
				}
			}
		}));

		var oCriticalThresholdCell = new sap.ui.commons.layout.MatrixLayoutCell({
			colSpan: 1 });

		oCriticalThresholdCell.addContent(new sap.ui.commons.TextField({
			editable : false,
			value : {
				parts : ["errorThreshold"],
				formatter : function(errorThreshold) {
					if(errorThreshold == 0) {
						return "";
					}

					return "C > " + errorThreshold;
				}
			}
		}));

		oThresholdCellMatrixLayout.createRow(oWarningThresholdCell, oCriticalThresholdCell);

		oMetricsTable.addColumn(new sap.ui.table.ColumnHeader({
			label: new sap.ui.commons.Label({text: "Thresholds"}),
			template: oThresholdCellMatrixLayout
		}));

		oMetricsTable.addColumn(new sap.ui.table.ColumnHeader({
			label: new sap.ui.commons.Label({text: "Last Checked"}),
			template: new sap.ui.commons.TextField({
				value: {
					parts : ["timestamp"],
					formatter : function(timestamp) {
					var oDate = new Date(timestamp);
					return oDate.toUTCString();
					}
				},
				editable : false
			})
		}));

		oMetricsTable.setModel(oItemModel);
		oMetricsTable.bindRows("metrics");

		oLayout.createRow(oMetricsTable);

		oTable.attachRowSelectionChange(function(oEvent) {
			// get the binding context of the first selected row
			var selectedRowContext = oEvent.getParameter("rowContext");

			oMetricsTable.clearSelection();
			oMetricsTable.setBindingContext(selectedRowContext);
		});

		return new sap.m.Page({
			title: "Application Details",
			content: [oLayout]
		});
	}

});