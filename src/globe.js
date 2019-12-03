exports.install = function(Vue) {
	Vue.prototype.getLogin = function(callback) {
		//全局函数1
        this.axios.post('user/isLogin',null,)
        .then(function(req){
            const data = req.data;
            console.log(data);
            if(data.success){
                callback(data.data);
            }else{
                callback(data.success);
            }
        })
        .catch(function(error) {
            console.log(error);
        });
    };
	Vue.prototype.Logout = function(callback) {
		//全局函数2
        this.axios.post('user/logout',null,)
        .then(function(req){
            const data = req.data;
            console.log(data.success);
            if(data.success){
                callback(data.success);
            }else{
                callback(data.success);
            }
        })
        .catch(function(error) {
            console.log(error);
        });
	};
	Vue.prototype.Post = function(url,data,callback) {
        //全局函数3
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
        //全局函数4
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
