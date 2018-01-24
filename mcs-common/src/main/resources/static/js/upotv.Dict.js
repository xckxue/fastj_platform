(function () {
    window.upotv = window.upotv ? window.upotv : {};
    upotv.biz = upotv.biz ? upotv.biz : {};
    upotv.biz.Dict = {};

    upotv.biz.Dict.getDict = function (typeCode,cache) {
        var dict = {};
        $.ajax({
            type: "POST",
            async: false,
            url: "../dict/getDict",
            data: {typeCode: typeCode,cache:cache},
            success: function (result) {
                dict = result;
            }
        });
        return dict;
    };

    upotv.biz.Dict.getValue = function (code, dictStore) {
        for (var k = 0; k < dictStore.length; k++) {
            if(dictStore[k]["codeId"] == code){
                return dictStore[k]["codeName"];
            }
        }
        return code;
    };

    upotv.biz.Dict.getMultiValue = function (value, dictStore) {
        if (value) {
            try {
                var val = value.split(",");
                var rtn = "";
                val.forEach(function (val, index, arr) {
                    var name = upotv.biz.Dict.getValue(val, dictStore);
                    if (name) {
                        rtn += "," + name;
                    } else {
                        rtn += "," + val;
                    }
                });
                return rtn.slice(1);
            } catch (err) {
                return "";
            }
        } else {
            return "";
        }
    };

})();