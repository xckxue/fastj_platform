/**
 * @requires jQuery,EasyUI
 *
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
    equalTo : {
        validator : function(value, param) {
            return value == $(param[0]).val();
        },
        message : '密码不一致！'
    },

    isPwd : {
        validator: function (value, param) {
            return  /^[a-zA-Z\d_]{6,18}$/.test(value);
        },
        message: '长度在6-18之间，只能包含字符、数字和下划线'
    }
});
