//price gpu
var price_rx470_8gb = 320
var price_gtx1060_3gb = 350;
var price_gtx1060_6gb = 400;
var price_gtx1070_8gb = 500;
var price_gtx1080ti_11gb = 800;
//price other
var bp_600W_GOLD = 4000;
var bp_750W_GOLD = 8000;
var riser_price = 300;


var size_recoupment_stack = 1;

var gpus = ["AMD RX 470 8GB", "GTX 1060 3GB", "GTX 1060 6GB", "GTX 1070 8GB", "GTX 1080TI 11GB"];
var gpus_price = [320, 350, 400, 500, 800];
$(document).ready(function () {
    for (i = 0; i < gpus.length; i++) {
        $("<option>" + gpus[i] + " (" + gpus_price[i] + "$)</option>").appendTo("#gpu");
    }
    update_price();
});


function add_recoupment_stack() {

    var str_gpu_name = $("#gpu option:selected").text() + " x " + $("#countgpu option:selected").val();
    var sum = parseFloat($("#gpu_sum").text()) + parseFloat($("#motherboard_sum").text()) + parseFloat($("#processor_sum").text()) + parseFloat($("#ram_sum").text()) +
        parseFloat($("#ssd_sum").text()) + parseFloat($("#bp_sum").text()) + parseFloat($("#riser_sum").text()) + parseFloat($("#case_sum").text());
    var intake_sum = (parseFloat($("#intake").val()) * 24 * 30 / 1000) * parseFloat($("#price_kwatt").val());
    var eth_roi = intake_sum * parseFloat($("#proc_intake_zec").val());

    //    ROI ETH
    var s1 = parseFloat($("#countgpu option:selected").val()) * parseFloat($("#currency").val()) * 30 * parseFloat($("#eth_day").val());
    var s2 = sum;
    var eth_roi = 0;
    while (s2 > 0) {
        s2 = s2 + (intake_sum / 100 * parseFloat($("#proc_intake_eth").val())) - s1;
        eth_roi++;
        if (eth_roi > 17) break;
    }
    //ROI ZEC
    var s1 = parseFloat($("#countgpu option:selected").val()) * parseFloat($("#currency").val()) * 30 * parseFloat($("#zec_day").val());
    var s2 = sum;
    var zec_roi = 0;
    while (s2 > 0) {
        s2 = s2 + (intake_sum / 100 * parseFloat($("#proc_intake_zec").val())) - s1;
        zec_roi++;
        if (zec_roi > 17) break;
    }
    //ROI COIN
    var s1 = parseFloat($("#countgpu option:selected").val()) * parseFloat($("#currency").val()) * 30 * parseFloat($("#coin_day").val());
    var s2 = sum;
    var coin_roi = 0;
    while (s2 > 0) {
        s2 = s2 + (intake_sum / 100 * parseFloat($("#proc_intake_coin").val())) - s1;
        coin_roi++;
        if (coin_roi > 17) break;
    }

    //    var add_str="";
    //    if(min_roi_eth>eth_roi){
    //        add_str = " class='danger' ";
    //    }



    $(" <tr ><td>" + size_recoupment_stack + "</td>" +
        "<td >" + str_gpu_name + "</td>" +
        "<td >" + sum + "</td>" +
        "<td >" + intake_sum + "</td>" +
        "<td id='id_eth_roi' " + ((eth_roi > 17) ? "class='danger'>>18" : ">" + eth_roi) + "</td>" +
        "<td id='id_zec_roi' " + ((zec_roi > 17) ? "class='danger'>>18" : ">" + zec_roi) + " </td>" +
        "<td id='id_coin_roi' " + ((coin_roi > 17) ? "class='danger'>>18" : ">" + coin_roi) + " </td></tr>")
        .appendTo("#recoupment_stack");

    var min_eth_roi = 18;
    $('#recoupment_table tr td:nth-child(5)').each(function () {
        if ($(this).text() === '>18') {} else {
            if (parseInt($(this).text()) <= min_eth_roi) {
                min_eth_roi = parseInt($(this).text());
            }
            $(this).parent('tr').html($(this).parent('tr').html().replace('id="id_eth_roi" class="success"', ' id="id_eth_roi" '));
        }

    });

    $('#recoupment_table tr td:nth-child(5)').each(function () {
        if ($(this).text() === '>18') {} else {
            if (parseInt($(this).text()) <= min_eth_roi) {
                $(this).parent('tr').html($(this).parent('tr').html().replace('id="id_eth_roi"', ' id="id_eth_roi" class="success"'));
            }
        }
    });

    var min_zec_roi = 18;
    $('#recoupment_table tr td:nth-child(6)').each(function () {
        if ($(this).text() === '>18') {} else {
            if (parseInt($(this).text()) <= min_zec_roi) {
                min_zec_roi = parseInt($(this).text());
            }
            $(this).parent('tr').html($(this).parent('tr').html().replace('id="id_zec_roi" class="success"', ' id="id_zec_roi" '));
        }

    });

    $('#recoupment_table tr td:nth-child(6)').each(function () {
        if ($(this).text() === '>18') {} else {
            if (parseInt($(this).text()) <= min_zec_roi) {
                $(this).parent('tr').html($(this).parent('tr').html().replace('id="id_zec_roi"', ' id="id_zec_roi" class="success"'));
            }
        }
    });

    var min_coin_roi = 18;
    $('#recoupment_table tr td:nth-child(7)').each(function () {
        if ($(this).text() === '>18') {} else {
            if (parseInt($(this).text()) <= min_coin_roi) {
                min_coin_roi = parseInt($(this).text());
            }
            $(this).parent('tr').html($(this).parent('tr').html().replace('id="id_coin_roi" class="success"', ' id="id_coin_roi" '));
        }

    });

    $('#recoupment_table tr td:nth-child(7)').each(function () {
        if ($(this).text() === '>18') {} else {
            if (parseInt($(this).text()) <= min_coin_roi) {
                $(this).parent('tr').html($(this).parent('tr').html().replace('id="id_coin_roi"', ' id="id_coin_roi" class="success"'));
            }
        }
    });
    size_recoupment_stack++;


}

function update_price() {

    var currency = $("#currency").val();
    var price_gpu = $("#price_gpu_top").val();
    console.log(price_gpu);
    var str_gpu = $("#gpu option:selected").text();
    var str_countgpu = $("#countgpu option:selected").val();
    var gpu_index = $("#gpu option:selected").index();

    $("#table").show(300);
    if (gpu_index == 0) {
        $("#gpu_name").html("Видеокарта AMD RX 470 8GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gpu * currency);
        $("#gpu_sum").html(price_gpu * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 1) {
        $("#gpu_name").html("Видеокарта GTX 1060 3GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gpu * currency);
        $("#gpu_sum").html(price_gpu * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 2) {
        $("#gpu_name").html("Видеокарта GTX 1060 6GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gpu * currency);
        $("#gpu_sum").html(price_gpu * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 3) {
        $("#gpu_name").html("Видеокарта GTX 1070 8GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gpu * currency);
        $("#gpu_sum").html(price_gpu * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 4) {
        $("#gpu_name").html("Видеокарта GTX 1080TI 11GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gpu * currency);
        $("#gpu_sum").html(price_gpu * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else {
        $("#table").hide();
    }

    if (str_countgpu == 4) {
        $("#bp_name").html("Блок питания 750-850W GOLD 12B > 60A");
        $("#bp_count").html("1");
        $("#bp_price").html("8000");
        $("#bp_sum").html("8000");
        if (gpu_index == 4) {
            $("#bp_name").html("Блок питания 750-850W GOLD 12B > 60A");
            $("#bp_count").html("2");
            $("#bp_price").html("8000");
            $("#bp_sum").html("16000");
        }
    } else if (str_countgpu == 6) {
        $("#bp_name").html("Блок питания 600-650W GOLD 12B > 45A");
        $("#bp_count").html("2");
        $("#bp_price").html("4000");
        $("#bp_sum").html("8000");
        if (gpu_index == 4) {
            $("#bp_name").html("Серверный блок питания >1600W");
            $("#bp_count").html("1");
            $("#bp_price").html("12000");
            $("#bp_sum").html("12000");
        }
    } else if (str_countgpu == 8) {
        $("#bp_name").html("Блок питания 750-850W GOLD 12B > 60A");
        $("#bp_count").html("2");
        $("#bp_price").html("8000");
        $("#bp_sum").html("16000");
        if (gpu_index == 4) {
            $("#bp_name").html("Серверный блок питания >2200W GOLD");
            $("#bp_count").html("1");
            $("#bp_price").html("18000");
            $("#bp_sum").html("18000");
        }
    } else {
        $("#table").hide();
    }

    var sum = parseFloat($("#gpu_sum").text()) + parseFloat($("#motherboard_sum").text()) + parseFloat($("#processor_sum").text()) + parseFloat($("#ram_sum").text()) +
        parseFloat($("#ssd_sum").text()) + parseFloat($("#bp_sum").text()) + parseFloat($("#riser_sum").text()) + parseFloat($("#case_sum").text());
    $("#total_sum").html("Потребление фермы: " + $("#intake").val() + "ваттЧас    / Итого сумма: " + sum + " рублей");

}


$("#gpu").change(function () {
    $("#table").hide();
    update_price();

});


$("#price_gpu_top").change(function () {
    $("#table").hide();
    update_price();

});

$("#countgpu").change(function () {
    $("#table").hide();
    update_price();

});

$("#currency").change(function () {
    $("#table").hide();
    update_price();

});
