const path = require("path");
const HTMLWebpackPlugin = require("html-webpack-plugin");

module.exports = (env) => {
  const mode = env.mode || "development";

  return {
    entry: "./src/index.tsx",
    mode: mode,
    devServer: {
      hot: true,
      client: {
        overlay: true,
      },
      historyApiFallback: true,
      compress: true,
      port: 9090,
    },
    devtool: "source-map",
    plugins: [
      new HTMLWebpackPlugin({
        template: "./src/index.html",
        inject: "body",
        scriptLoading: "defer",
        favicon: "./src/favicon.ico",
      }),
    ],
    module: {
      rules: [
        {
          test: /\.(js|jsx)$/,
          exclude: /node_modules/,
          use: "babel-loader",
        },
        // Compile TypeScript
        {
          test: /\.tsx?$/,
          exclude: /node_modules/,
          use: "ts-loader",
        },
        // Compile css
        {
          test: /\.css$/,
          use: ["style-loader", "css-loader"],
        },
        // Compile less
        {
          test: /\.less$/,
          use: ["style-loader", "css-loader", "less-loader"],
        },
        // Load URLs
        {
          test: /\.(jpg|png|gif|jpeg|woff|woff2|eot|ttf|svg¢)$/,
          use: "url-loader",
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
};
