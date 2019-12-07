exports.install = function(Vue) {
	Vue.prototype.getLogin = function(callback) {
		//全局函数1
        this.axios.post('user/isLogin',null)
        .then(function(req){
            const data = req.data;
            if(req){
                callback(data.data);
            }else{
                alert("wrong");
            }
        })
        .catch(function(error) {
        });
    };
	Vue.prototype.Logout = function(callback) {
		//全局函数2
        this.axios.post('user/logout',null,)
        .then(function(req){
            const data = req.data;
            if(data.success){
                callback(data);
            }else{
                alert("wrong");
            }
        })
        .catch(function(error) {
        });
	};
	Vue.prototype.Post = function(url,data,callback) {
        //全局函数3
        this.axios.post(url,data)
        .then(function(req){
            if(req.status==200){
                callback(req.data);
            }else{
                alert("wrong");
            }
        })
        .catch(function(error) {
        });
    };
    Vue.prototype.Get = function(url,params,callback) {
        //全局函数4
        this.axios.get(url,{params})
        .then(function(req){
            if(req.status==200){
                callback(req.data);
            }else{
                alert("wrong");
            }
        })
        .catch(function(error) {
        });
    };
    Vue.prototype.Delete = function(url,data,callback) {
        //全局函数4
        this.axios.delete(url,{"data":data})
        .then(function(req){
            if(req.status==200){
                callback(req.data);
            }else{
                alert("wrong")
            }
        })
        .catch(function(error) {
        });
    };
    Vue.prototype.Clone =function(data){
        //全局函数5
        return JSON.parse(JSON.stringify(data));
    }
};
