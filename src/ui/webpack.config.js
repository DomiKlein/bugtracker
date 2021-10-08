const path = require("path");
const HTMLWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  entry: "./src/index.ts",
  mode: "development",
  plugins: [
    new HTMLWebpackPlugin({
      template: "./src/index.html",
      inject: "head",
      scriptLoading: "defer",
    }),
  ],
  module: {
    rules: [
      // Compile TypeScript
      {
        test: /\.tsx?$/,
        use: "ts-loader",
        exclude: /node_modules/,
      },
      // Compile less
      {
        test: /\.less$/,
        use: [
          "style-loader", // creates style nodes from JS strings
          "css-loader", // translates CSS into CommonJS
          "less-loader", // compiles Less to CSS
        ],
        exclude: /node_modules/,
      },
      // Load HTML
      {
        test: /\.html$/,
        loader: "html-loader",
      },
    ],
  },
  resolve: {
    extensions: [".tsx", ".ts", ".js"],
  },
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname, "dist"),
  },
};
