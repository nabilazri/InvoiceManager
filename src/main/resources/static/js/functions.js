$(document).ready(function () {
    console.log("in");
    $("#select_produit").autocomplete({

        source: function (request, response) {
            let produitId = $('#select_produit').val();
            $.ajax({
                url: "/produit/charger-produit/" + produitId,
                dataType: "json",
                data: {
                    id: produitId
                },
                success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            value: item.id,
                            label: item.name,
                            price: item.price,
                        };
                    }));
                },
            });
            console.log(produitId);
        },
        select: function (event, ui) {
            //$("#buscar_producto").val(ui.item.label);

            if (itemsHelper.hasProduit(ui.item.value)) {
                itemsHelper.incrementerQuantité(ui.item.value, ui.item.price);
                return false;
            }

            let ligneFacture = $("#ligneFactureForm").html();

            ligneFacture = ligneFacture.replace(/{ID}/g, ui.item.value);
            ligneFacture = ligneFacture.replace(/{NAME}/g, ui.item.label);
            ligneFacture = ligneFacture.replace(/{PRICE}/g, ui.item.price);

            $("#getItemsProduit tbody").append(ligneFacture);
            itemsHelper.calculerSubtotal(ui.item.value, ui.item.price, 1);

            return false;
        }
    });

    $("form").submit(function () {
        $("#ligneFactureForm").remove();
        return;
    });

});
let itemsHelper = {
    calculerSubtotal: function (id, price, quantité) {
        $("#subtotal" + id).html(parseInt(price) * parseInt(quantité));
        this.calculerTotalTTC();
    },
    hasProduit: function (id) {
        let result = false;
        $('input[name="item_id[]"]').each(function () {
            if (parseInt(id) == parseInt($(this).val())) {
                result = true;
            }
        });
        return result;
    },
    incrementerQuantité: function (id, price) {
        let quantité = $("#quantité_" + id).val() ? parseInt($("#quantité_" + id).val()) : 0;
        $("#quantité_" + id).val(++quantité);
        this.calculerSubtotal(id, price, quantité);
    },
    supprimerLigneFacture: function (id) {
        $("#row_" + id).remove();
        this.calculerTotalTTC();
    },
    calculerTotalTTC: function () {
        let total = 0;

        $('span[id^="subtotal_"]').each(function () {
            total += parseInt($(this).html());
        });

        $("#total_ttc").html(total);
    }
};