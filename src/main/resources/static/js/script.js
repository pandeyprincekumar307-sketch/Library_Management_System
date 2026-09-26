// =========================================
// Library Management System
// Premium JavaScript
// Part - 1
// =========================================

document.addEventListener("DOMContentLoaded", () => {

    /* ==========================
            PAGE LOADER
    ========================== */

    const loader = document.getElementById("loader");

    window.addEventListener("load", () => {

        if(loader){

            loader.style.opacity = "0";

            setTimeout(()=>{

                loader.style.display="none";

            },500);

        }

    });


    /* ==========================
          WELCOME TOAST
    ========================== */

    const toast = document.getElementById("toast");
    const message = document.getElementById("toastMessage");

    function showToast(text){

        if(!toast) return;

        message.innerHTML=text;

        toast.classList.add("show");

        setTimeout(()=>{

            toast.classList.remove("show");

        },3000);

    }

    setTimeout(()=>{

        showToast("👋 Welcome To Library Management System");

    },800);


    /* ==========================
            SCROLL TOP
    ========================== */

    const scrollBtn=document.getElementById("scrollTop");

    window.addEventListener("scroll",()=>{

        if(window.scrollY>250){

            scrollBtn.style.display="block";

        }

        else{

            scrollBtn.style.display="none";

        }

    });

    if(scrollBtn){

        scrollBtn.onclick=()=>{

            window.scrollTo({

                top:0,

                behavior:"smooth"

            });

        }

    }


    /* ==========================
           DARK MODE
    ========================== */

    const themeBtn=document.getElementById("themeBtn");

    if(localStorage.getItem("theme")=="dark"){

        document.body.classList.add("dark-mode");

        if(themeBtn){

            themeBtn.innerHTML="☀";

        }

    }

    if(themeBtn){

        themeBtn.addEventListener("click",()=>{

            document.body.classList.toggle("dark-mode");

            if(document.body.classList.contains("dark-mode")){

                localStorage.setItem("theme","dark");

                themeBtn.innerHTML="☀";

            }

            else{

                localStorage.setItem("theme","light");

                themeBtn.innerHTML="🌙";

            }

        });

    }


    /* ==========================
           DELETE ALERT
    ========================== */

    const deletes=document.querySelectorAll(".delete-btn");

    deletes.forEach(btn=>{

        btn.addEventListener("click",(e)=>{

            const ok=confirm("Delete this record ?");

            if(!ok){

                e.preventDefault();

            }

            else{

                showToast("🗑 Record Deleted");

            }

        });

    });


    /* ==========================
        BUTTON RIPPLE EFFECT
    ========================== */

    const buttons=document.querySelectorAll(".btn");

    buttons.forEach(button=>{

        button.addEventListener("click",function(e){

            let ripple=document.createElement("span");

            ripple.className="ripple";

            this.appendChild(ripple);

            let x=e.clientX-this.offsetLeft;

            let y=e.clientY-this.offsetTop;

            ripple.style.left=x+"px";

            ripple.style.top=y+"px";

            setTimeout(()=>{

                ripple.remove();

            },700);

        });

    });

});
/*=====================================
        LIVE SEARCH
=====================================*/

const searchBox=document.getElementById("searchBox");

if(searchBox){

    searchBox.addEventListener("keyup",()=>{

        let value=searchBox.value.toLowerCase();

        let rows=document.querySelectorAll("table tbody tr");

        rows.forEach(row=>{

            let text=row.innerText.toLowerCase();

            if(text.indexOf(value)>-1){

                row.style.display="";

            }

            else{

                row.style.display="none";

            }

        });

    });

}

/*=====================================
      INPUT ANIMATION
=====================================*/

const inputs=document.querySelectorAll("input,select,textarea");

inputs.forEach(input=>{

    input.addEventListener("focus",()=>{

        input.style.transform="scale(1.02)";

    });

    input.addEventListener("blur",()=>{

        input.style.transform="scale(1)";

    });

});

/*=====================================
      CARD HOVER EFFECT
=====================================*/

const cards=document.querySelectorAll(".card");

cards.forEach(card=>{

    card.addEventListener("mousemove",(e)=>{

        const rect=card.getBoundingClientRect();

        const x=e.clientX-rect.left;

        const y=e.clientY-rect.top;

        card.style.background=

            `radial-gradient(circle at ${x}px ${y}px,
        rgba(37,99,235,.18),
        white 60%)`;

    });

    card.addEventListener("mouseleave",()=>{

        card.style.background="white";

    });

});

/*=====================================
      NUMBER COUNTER
=====================================*/

const numbers=document.querySelectorAll(".counter");

numbers.forEach(counter=>{

    let target=parseInt(counter.dataset.target);

    let count=0;

    let speed=target/120;

    function update(){

        if(count<target){

            count+=speed;

            counter.innerHTML=Math.floor(count);

            requestAnimationFrame(update);

        }

        else{

            counter.innerHTML=target;

        }

    }

    update();

});

/*=====================================
      TABLE ROW ANIMATION
=====================================*/

const rows=document.querySelectorAll("tbody tr");

rows.forEach((row,index)=>{

    row.style.opacity="0";

    row.style.transform="translateY(20px)";

    setTimeout(()=>{

        row.style.transition=".5s";

        row.style.opacity="1";

        row.style.transform="translateY(0)";

    },index*120);

});

/*=====================================
      AUTO FOCUS
=====================================*/

const firstInput=document.querySelector("form input");

if(firstInput){

    firstInput.focus();

}

/*=====================================
      BUTTON CLICK EFFECT
=====================================*/

const allButtons=document.querySelectorAll(".btn");

allButtons.forEach(btn=>{

    btn.addEventListener("mousedown",()=>{

        btn.style.transform="scale(.95)";

    });

    btn.addEventListener("mouseup",()=>{

        btn.style.transform="scale(1)";

    });

});

/*=====================================
       PAGE TITLE EFFECT
=====================================*/

const title=document.querySelector(".hero h1");

if(title){

    let txt=title.innerHTML;

    title.innerHTML="";

    let i=0;

    function typing(){

        if(i<txt.length){

            title.innerHTML+=txt.charAt(i);

            i++;

            setTimeout(typing,55);

        }

    }

    typing();

}