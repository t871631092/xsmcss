const webpack = require("webpack");
module.exports = {
	//publicPath: '/', // 相对于 HTML 页面（目录相同）
	configureWebpack: {
		plugins: [
			new webpack.ProvidePlugin({
				$: "jquery",
				jQuery: "jquery",
				"window.jQuery": "jquery",
				Popper: ["popper.js", "default"]
			})
		]
    }
};
