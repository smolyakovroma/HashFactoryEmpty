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

var gpus = ["AMD RX 470 8GB", "GTX 1060 3GB", "GTX 1060 6GB", "GTX 1070 8GB", "GTX 1080TI 11GB"];
var gpus_price = [320, 350, 400, 500, 800];
$(document).ready(function () {
    for (i = 0; i < gpus.length; i++) {
        $("<option>" + gpus[i] + " (" + gpus_price[i] + "$)</option>").appendTo("#gpu");
    }
    update_price();
});



function update_price() {

    var currency = $("#currency").val();
    var str_gpu = $("#gpu option:selected").text();
    var str_countgpu = $("#countgpu option:selected").val();
    var gpu_index = $("#gpu option:selected").index();
    $("#table").show(300);
    if (gpu_index == 0) {
        $("#gpu_name").html("Видеокарта AMD RX 470 8GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_rx470_8gb * currency);
        $("#gpu_sum").html(price_rx470_8gb * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 1) {
        $("#gpu_name").html("Видеокарта GTX 1060 3GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gtx1060_3gb * currency);
        $("#gpu_sum").html(price_gtx1060_3gb * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 2) {
        $("#gpu_name").html("Видеокарта GTX 1060 6GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gtx1060_6gb * currency);
        $("#gpu_sum").html(price_gtx1060_6gb * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 3) {
        $("#gpu_name").html("Видеокарта GTX 1070 8GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gtx1070_8gb * currency);
        $("#gpu_sum").html(price_gtx1070_8gb * currency * str_countgpu);
        $("#riser_count").html(str_countgpu);
        $("#riser_sum").html(riser_price * str_countgpu);
    } else if (gpu_index == 4) {
        $("#gpu_name").html("Видеокарта GTX 1080TI 11GB");
        $("#gpu_count").html(str_countgpu);
        $("#gpu_price").html(price_gtx1080ti_11gb * currency);
        $("#gpu_sum").html(price_gtx1080ti_11gb * currency * str_countgpu);
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
    $("#total_sum").html("Итого сумма: " + sum + " рублей");

}


$("#gpu").change(function () {
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
