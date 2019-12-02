exports.install = function(Vue) {
	Vue.prototype.getLogin = function(callback) {
		//全局函数1
        this.axios.post('/isLogin',null,)
        .then(function(req){
            const data = req.data;
            console.log(data);
            if(data.success){
                callback(data.data);
            }else{
                alert("wrong")
            }
        })
        .catch(function(error) {
            console.log(error);
        });
	};
	Vue.prototype.Post = function(url,data,callback) {
        //全局函数2
        this.axios.post(url,data)
        .then(function(req){
            const data = req.data;
            console.log(data);
            if(data.success){
                callback(data.data);
            }else{
                alert("wrong")
            }
        })
        .catch(function(error) {
            console.log(error);
        });
    };
    Vue.prototype.Get = function(url,callback) {
        //全局函数2
        this.axios.get(url)
        .then(function(req){
            const data = req.data;
            console.log(data);
            if(data.success){
                callback(data.data);
            }else{
                alert("wrong")
            }
        })
        .catch(function(error) {
            console.log(error);
        });
    };
};
