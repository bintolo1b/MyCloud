<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="style.css">
    <title>Toast Notification</title>
</head>
<body>
    <div id="toast">
    </div>

    <div>
        <div onclick="showSuccessToast();" class="btn btn--success">Show success toast</div>
        <div onclick="showErrorToast();" class="btn btn--error">Show error toast</div>
    </div>

    <script>
        function toast({title = '',message = '',type = 'infor',duration = 3000}) {
            const main = document.getElementById('toast');
            if(main) {
                const toast = document.createElement('div');

                const autoRemoveID = setTimeout(function() {
                    main.removeChild(toast);
                }, duration + 1000);

                toast.onclick = function(e) {
                    if(e.target.closest('.toast__close')){
                        main.removeChild(toast);
                        clearTimeout(autoRemoveID);
                    }
                }

                const icons = {
                    success: 'fas fa-check-circle',
                    info: 'fas fa-info-circle',
                    warning: 'fas fa-exclamation-circle',
                    error: 'fas fa-exclamation-circle'
                };
                const icon = icons[type];
                const delay = (duration/1000).toFixed(2);

                toast.classList.add('toast', `toast--${type}`);
                toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;

                toast.innerHTML = `
                <div class="toast__icon">
                <i class="${icon}"></i>
                </div>
                <div class="toast__body">
                 <h3 class="toast__title">${title}</h3>
                <p class="toast__msg">${message}</p>
                </div>
                <div class="toast__close">
                <i class="fa-solid fa-xmark"></i>
                </div>
                `;
                main.appendChild(toast);
            }
        }

        function showSuccessToast(){
            toast({
            title: 'Success',
            message: 'Successful!',
            type: 'success',
            duration: 3000
        });
        }
        function showErrorToast(){
            toast({
            title: 'Error',
            message: 'Error!',
            type: 'error',
            duration: 3000
        });
        }
    </script>
</body>
</html>
