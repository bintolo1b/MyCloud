function Validator(formSelector) {
    var _this = this;

    function getParent(element, selector) {
        while(element.parentElement) {
            if(element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }


    var formRules = {};

    /*
        Quy uoc tao rule: Co loi thi return 'errorMessage'
    */ 
    var validatorRules = {
        required: function (value) {
            return value ? undefined : 'This field is required';
        },
        email: function (value) {
            var regrex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regrex.test(value) ? undefined : 'Please enter a valid email';
        },
        min: function (min) {
            return function (value) {
                return value.length >= min ? undefined : `Please enter at least ${min} characters`;
            }
        },
        max: function (max) {
            return function (value) {
                return value.length <= max ? undefined : `Vui lòng nhập tối đa ${max} kí tự`;
            }
        }
    };

    // Lấy ra form element trong dom theo 'formSelector'
    var formElement = document.querySelector(formSelector);
    
    if (formElement) {

        var inputs = formElement.querySelectorAll('[name][rules]');
        for(var input of inputs) {
            var rules = input.getAttribute('rules').split('|');
            for(var rule of rules) {
                var ruleInfor;
                var isRuleHashValue = rule.includes(':');

                if (isRuleHashValue) {
                    ruleInfor = rule.split(':');
                    rule = ruleInfor[0];

                }

                var ruleFunc = validatorRules[rule];

                if (isRuleHashValue) {
                    ruleFunc = ruleFunc(ruleInfor[1]);
                }

                if (Array.isArray(formRules[input.name])) {
                    formRules[input.name].push(ruleFunc);
                } else {
                    formRules[input.name] = [ruleFunc];
                }
            }

            // Lắng nghe sự kiện để validate (blur, change...)

            input.onblur = handleValidate;
            input.oninput = handleClearError;
        }

        function handleValidate(event) {
            var rules = formRules[event.target.name];
            var errorMessage;

            for(var rule of rules) {
                errorMessage = rule(event.target.value);
                if(errorMessage) break;
            }

            // Nếu có lỗi thì hiển thị lỗi ra UI
            if (errorMessage) {
                var formGroup = getParent(event.target, '.form-group');
                if(formGroup) {
                    formGroup.classList.add('invalid');
                    var formMessage = formGroup.querySelector('.form-message');
                    if(formMessage) {
                        formMessage.innerText = errorMessage;
                    }
                }
            }
            
            return !errorMessage;
        }

        // Clear error
        function handleClearError(event) {
            var formGroup = getParent(event.target, '.form-group');
            if(formGroup.classList.contains('invalid')) {
                formGroup.classList.remove('invalid');
            }
            var formMessage = formGroup.querySelector('.form-message');
            if(formMessage) {
                formMessage.innerText = '';
            }
        }
    }

    //Xử lý hành vi submit form
    formElement.onsubmit = function (event) {
        event.preventDefault();

        console.log(_this);

        var inputs = formElement.querySelectorAll('[name][rules]');
        var isValid = true;

        for(var input of inputs) {
            if(!handleValidate({target:input})) {
                isValid = false;
            }
        }

        //Khi không có lỗi thì submit form
        if (isValid) {
            if(typeof _this.onSubmit === 'function') {
                var enableInputs = formElement.querySelectorAll('[name]');

                     var formValues = Array.from(enableInputs).reduce(function(values, input) {

                        switch(input.type) {
                            case 'radio':
                                values[input.name] = formElement.querySelector('input[name="' + input.name + '"]:checked').value;
                                break;
                            case 'checkbox':
                                if(! input.matches(':checked')) {
                                    values[input.name] = '';
                                    return values;
                                };
                                if (!Array.isArray(values[input.name])) {
                                    values[input.name] = [];
                                }
                                values[input.name].push(input.value);
                                break;
                            case 'file':
                                values[input.name] = input.files;
                                break;
                            default:
                                values[input.name] = input.value;
                        }

                        return values;
                     }, {});

                     _this.onSubmit(formValues);
            } else {
                formElement.submit();
            }
        }
    }
}