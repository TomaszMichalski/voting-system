import React from "react";
import CanvasJSReact from "./canvasjs.react";
const CanvasJSChart = CanvasJSReact.CanvasJSChart;

export const Chart = ({title, chartData}) => {
  const options = {
    animationEnabled: true,
    exportEnabled: true,
    theme: "light1", //"light1", "dark1", "dark2"
    title: {
      text: title,
    },
    data: [
      {
        type: "column", //change type to bar, line, area, pie, etc
        //indexLabel: "{y}", //Shows y value on all Data Points
        indexLabelFontColor: "#5A5757",
				indexLabelPlacement: "outside",
				color: "#11385B",
				dataPoints: chartData
      },
    ],
  };

  return (
    <div>
      <CanvasJSChart
        options={options}
        /* onRef={ref => this.chart = ref} */
      />
      {/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
    </div>
  );
};
