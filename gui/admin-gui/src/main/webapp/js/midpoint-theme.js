/*
 * Copyright (c) 2010-2015 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

window.onload = initAjaxStatusSigns;
function clickFuncWicket6(eventData) {
    var clickedElement = (window.event) ? event.srcElement : eventData.target;
    if ((clickedElement.tagName.toUpperCase() == 'BUTTON' || clickedElement.tagName.toUpperCase() == 'A' || clickedElement.parentNode.tagName.toUpperCase() == 'A'
        || (clickedElement.tagName.toUpperCase() == 'INPUT' && (clickedElement.type.toUpperCase() == 'BUTTON' || clickedElement.type.toUpperCase() == 'SUBMIT')))
        && clickedElement.parentNode.id.toUpperCase() != 'NOBUSY' ) {
        showAjaxStatusSign();
    }
}

function initAjaxStatusSigns() {
    document.getElementsByTagName('body')[0].onclick = clickFuncWicket6;
    hideAjaxStatusSign();
    Wicket.Event.subscribe('/ajax/call/beforeSend', function( attributes, jqXHR, settings ) {
        showAjaxStatusSign();
    });
    Wicket.Event.subscribe('/ajax/call/complete', function( attributes, jqXHR, textStatus) {
        hideAjaxStatusSign();
    });
}

function showAjaxStatusSign() {
    document.getElementById('ajax_busy').style.display = 'inline';
}

function hideAjaxStatusSign() {
    document.getElementById('ajax_busy').style.display = 'none';
}


/**
 * InlineMenu initialization function
 */
function initInlineMenu(menuId, hideByDefault) {
    var menu = $('#' + menuId).find('ul.cog');

    var parent = menu.parent().parent();     //this is inline menu div
    if (!hideByDefault && !isCogInTable(parent)) {
        return;
    }

    if (isCogInTable(parent)) {
        //we're in table, we now look for <tr> element
        parent = parent.parent('tr');
    }

    // we only want to hide inline menus that are in table <td> element,
    // inline menu in header must be visible all the time, or every menu
    // that has hideByDefault flag turned on
    menu.hide();

    parent.hover(function () {
        //over
        menu.show();
    }, function () {
        //out
        menu.hide();
    })
}

function isCogInTable(inlineMenuDiv) {
    return inlineMenuDiv.hasClass('cog') && inlineMenuDiv[0].tagName.toLowerCase() == 'td';
}

function updateHeight(elementId, add, substract) {
    updateHeightReal(elementId, add, substract);
    $(window).resize(function() {
        updateHeightReal(elementId, add, substract);
    });
}

function updateHeightReal(elementId, add, substract) {
    $('#' + elementId).css("height","0px");

    var documentHeight = $(document).innerHeight();
    var elementHeight = $('#' + elementId).outerHeight(true);
    var mainContainerHeight = $('section.content-header').outerHeight(true)
        + $('section.content').outerHeight(true) + $('footer.main-footer').outerHeight(true)
        + $('header.main-header').outerHeight(true);

    console.log("Document height: " + documentHeight + ", mainContainer: " + mainContainerHeight);

    var height = documentHeight - mainContainerHeight - elementHeight;
    console.log("Height clean: " + height);

    if (substract instanceof Array) {
        for (var i = 0; i < substract.length; i++) {
            console.log("Substract height: " + $(substract[i]).outerHeight(true));
            height -= $(substract[i]).outerHeight(true);
        }
    }
    if (add instanceof Array) {
        for (var i = 0; i < add.length; i++) {
            console.log("Add height: " + $(add[i]).outerHeight(true));
            height += $(add[i]).outerHeight(true);
        }
    }
    console.log("New css height: " + height);
    $('#' + elementId).css("height", height + "px");
}

/**
 * Used in TableConfigurationPanel (table page size)
 *
 * @param buttonId
 * @param popoverId
 * @param positionId
 */
function initPageSizePopover(buttonId, popoverId, positionId) {
    console.log("initPageSizePopover('" + buttonId + "','" + popoverId + "','" + positionId +"')");

    var button = $('#' + buttonId);
    button.click(function () {
        var popover = $('#' + popoverId);

        var positionElement = $('#' + positionId);
        var position = positionElement.position();

        var top = position.top + parseInt(positionElement.css('marginTop'));
        var left = position.left + parseInt(positionElement.css('marginLeft'));
        var realPosition = {top: top, left: left};

        var left = realPosition.left - popover.outerWidth();
        var top = realPosition.top + button.outerHeight() / 2 - popover.outerHeight() / 2;

        popover.css("top", top);
        popover.css("left", left);

        popover.toggle();
    });
}

/**
 * Used in SearchPanel for advanced search, if we want to store resized textarea dimensions.
 * 
 * @param textAreaId
 */
function storeTextAreaSize(textAreaId) {
    console.log("storeTextAreaSize('" + textAreaId + "')");

    var area = $('#' + textAreaId);
    $.textAreaSize = [];
    $.textAreaSize[textAreaId] = {
        height: area.height(), 
        width: area.width(),
        position: area.prop('selectionStart')
    }
}

/**
 * Used in SearchPanel for advanced search, if we want to store resized textarea dimensions.
 * 
 * @param textAreaId
 */
function restoreTextAreaSize(textAreaId) {
    console.log("restoreTextAreaSize('" + textAreaId + "')");

    var area = $('#' + textAreaId);

    var value = $.textAreaSize[textAreaId];

    area.height(value.height);
    area.width(value.width);
    area.prop('selectionStart', value.position);

    // resize also error message span
    var areaPadding = 70;
    area.siblings('.help-block').width(value.width + areaPadding);
}