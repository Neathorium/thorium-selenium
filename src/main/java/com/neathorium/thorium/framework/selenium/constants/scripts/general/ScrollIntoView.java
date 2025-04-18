package com.neathorium.thorium.framework.selenium.constants.scripts.general;

import com.neathorium.thorium.framework.selenium.namespaces.scripter.Globals;
import com.neathorium.thorium.framework.selenium.namespaces.scripter.General;

import java.util.Arrays;

public abstract class ScrollIntoView {
    public static final String FUNCTION_NAME = "scrollIntoView_s",
        FUNCTION_BEHAVIOR_PARAMS = "GLOBALS.scrollBehaviorParameters",
        FUNCTION_GROUP = "_SCROLL_INTO_VIEW",
        IS_EXISTS = Globals.getFunctionExists(FUNCTION_GROUP, FUNCTION_NAME),
        EXECUTE = String.join("\n", Arrays.asList(
            "let scrollTarget = arguments[0];",
            "let lastPosition = null;",
            "document['__is_scrolled_into_view'] = false;",
            "scrollTarget.scrollIntoView({behavior:'smooth', block: 'center'});",
            "function checkPositionFunction() {",
            "    const newPosition = scrollTarget.getBoundingClientRect().top;",
            "    if (newPosition === lastPosition) {",
            "        document['__is_scrolled_into_view'] = true;",
            "    } else {",
            "        lastPosition = newPosition;",
            "        requestAnimationFrame(checkPositionFunction);",
            "    }",
            "}",

            "requestAnimationFrame(checkPositionFunction);",
            "return true;"
        )),
        CHECK = String.join("\n", Arrays.asList(
            "let rect = arguments[0].getBoundingClientRect();\n",
            "\n",
            "return (\n",
            "    Math.round(rect.top) >= 0 &&\n",
            "    Math.round(rect.left) >= 0 &&\n",
            "    Math.round(rect.bottom) <= (window.innerHeight || document.documentElement.clientHeight) && /* or $(window).height() */\n",
            "    Math.round(rect.right) <= (window.innerWidth || document.documentElement.clientWidth) /* or $(window).width() */\n",
            ");"
        )),
        SET_FUNCTIONS = (
            GeneralSnippets.STRICT +
            "return true;\n /*" +
            Globals.initialize(FUNCTION_GROUP) +
            "GLOBALS.isElement = function isElementFunction(el) {" +
            "    " + General.RETURN("typeof el === 'object' && el.nodeType === 1") +
            "};" +
            "GLOBALS.canOverflow = function canOverflowFunction(overflow, skipOverflowHiddenElements) {" +
                General.IF_RETURN("skipOverflowHiddenElements && overflow === 'hidden'", "false")  +
                General.RETURN("((overflow !== 'visible') && (overflow !== 'clip'))") +
            "};" +
            "GLOBALS.isScrollable = function isScrollableFunction(el, skipOverflowHiddenElements) {" +
            "    var canOverflow," +
            "        style;" +
            "    if (el.clientHeight < el.scrollHeight || el.clientWidth < el.scrollWidth) {" +
            "        style = getComputedStyle(el, null);" +
            "        canOverflow = GLOBALS.canOverflow;" +
            "        return canOverflow(style.overflowY, skipOverflowHiddenElements) || canOverflow(style.overflowX, skipOverflowHiddenElements);" +
            "    }" +
            "" +
            GeneralSnippets.RETURN_FALSE +
            "};" +
            "" +
            "GLOBALS.alignNearest = function alignNearestFunction(scrollingEdgeStart, scrollingEdgeEnd, scrollingSize, scrollingBorderStart, scrollingBorderEnd, elementEdgeStart, elementEdgeEnd, elementSize) {" +
                General.IF_RETURN("elementEdgeStart < scrollingEdgeStart && elementEdgeEnd > scrollingEdgeEnd || elementEdgeStart > scrollingEdgeStart && elementEdgeEnd < scrollingEdgeEnd", "0") +
                General.IF_RETURN("elementEdgeStart <= scrollingEdgeStart && elementSize <= scrollingSize || elementEdgeEnd >= scrollingEdgeEnd && elementSize >= scrollingSize", "elementEdgeStart - scrollingEdgeStart - scrollingBorderStart") +
                General.IF_RETURN("elementEdgeEnd > scrollingEdgeEnd && elementSize < scrollingSize || elementEdgeStart < scrollingEdgeStart && elementSize > scrollingSize", "elementEdgeEnd - scrollingEdgeEnd + scrollingBorderEnd") +
                GeneralSnippets.RETURN_ZERO +
            "};" +
            "" +
            "GLOBALS.compute = function computingFunction(target, options) {" +
            "    var scrollMode = options.scrollMode," +
            "        block = options.block," +
            "        inline = options.inline," +
            "        boundary = options.boundary," +
            "        skipOverflowHiddenElements = options.skipOverflowHiddenElements;" +
            "    var checkBoundary = typeof boundary === 'function' ? boundary : function (node) {" +
            "        return node !== boundary;" +
            "    };" +
                General.IF_THEN("!GLOBALS.isElement(target)", " throw new TypeError('Invalid target');") +
            "    var scrollingElement = document.scrollingElement || document.documentElement;" +
            "    var frames = [];" +
            "    var cursor = target;" +
            "    var scrollable = GLOBALS.isScrollable;" +
            "    while (GLOBALS.isElement(cursor) && checkBoundary(cursor)) {" +
            "        cursor = cursor.parentNode;" +
            "        if (cursor === scrollingElement) {" +
            "            frames.push(cursor);" +
            "            break;" +
            "        }" +
            "        " + General.IF_CONTINUE("cursor === document.body && scrollable(cursor) && !scrollable(document.documentElement)") +
            "        " + General.IF_THEN("scrollable(cursor, skipOverflowHiddenElements)", "frames.push(cursor);") +
            "    }" +
            "" +
            "    var viewportWidth = window.visualViewport ? visualViewport.width : innerWidth;" +
            "    var viewportHeight = window.visualViewport ? visualViewport.height : innerHeight;" +
            "    var viewportX = window.scrollX || pageXOffset;" +
            "    var viewportY = window.scrollY || pageYOffset;" +
            "" +
            "    var bcr = target.getBoundingClientRect()," +
            "        targetHeight = bcr.height," +
            "        targetWidth = bcr.width," +
            "        targetTop = bcr.top," +
            "        targetRight = bcr.right," +
            "        targetBottom = bcr.bottom," +
            "        targetLeft = bcr.left;" +
            "" +
            "    var targetBlock = block === 'start' || block === 'nearest' ? targetTop : block === 'end' ? targetBottom : targetTop + targetHeight / 2;" +
            "    var targetInline = inline === 'center' ? targetLeft + targetWidth / 2 : inline === 'end' ? targetRight : targetLeft;" +
            "    var computations = [];" +
            "    var alignNearest = GLOBALS.alignNearest;" +
            "    " +
            "    var length = frames.length;" +
            "    for (var index = 0; index < length; ++index) {" +
            "        var frame = frames[index];" +
            "        var bcli = frame.getBoundingClientRect()," +
            "            _height = bcli.height," +
            "            _width = bcli.width," +
            "            _top = bcli.top," +
            "            right = bcli.right," +
            "            bottom = bcli.bottom," +
            "            _left = bcli.left;" +
            "" +
            "        " + General.IF_RETURN("scrollMode === 'if-needed' && targetTop >= 0 && targetLeft >= 0 && targetBottom <= viewportHeight && targetRight <= viewportWidth && targetTop >= _top && targetBottom <= bottom && targetLeft >= _left && targetRight <= right", "computations") +
            "        var frameStyle = getComputedStyle(frame);" +
            "        var borderLeft = parseInt(frameStyle.borderLeftWidth, 10);" +
            "        var borderTop = parseInt(frameStyle.borderTopWidth, 10);" +
            "        var borderRight = parseInt(frameStyle.borderRightWidth, 10);" +
            "        var borderBottom = parseInt(frameStyle.borderBottomWidth, 10);" +
            "        var blockScroll = 0;" +
            "        var inlineScroll = 0;" +
            "        var scrollbarWidth = 'offsetWidth' in frame ? frame.offsetWidth - frame.clientWidth - borderLeft - borderRight : 0;" +
            "        var scrollbarHeight = 'offsetHeight' in frame ? frame.offsetHeight - frame.clientHeight - borderTop - borderBottom : 0;" +
            "" +
            "        if (scrollingElement === frame) {" +
            "            blockScroll = (" +
            "                ((block === 'start') && targetBlock) ||" +
            "                ((block === 'end') && targetBlock - viewportHeight) ||" +
            "                ((block === 'nearest') && GLOBALS.alignNearest(" +
            "                    viewportY," +
            "                    viewportY + viewportHeight," +
            "                    viewportHeight," +
            "                    borderTop," +
            "                    borderBottom," +
            "                    viewportY + targetBlock," +
            "                    viewportY + targetBlock + targetHeight," +
            "                    targetHeight" +
            "                )) ||" +
            "                (targetBlock - viewportHeight / 2)" +
            "            );" +
            "" +
            "            inlineScroll = (" +
            "                ((inline === 'start') && targetInline) ||" +
            "                ((inline === 'end') && (targetInline - viewportWidth)) || " +
            "                ((inline === 'center') && (targetInline - viewportWidth / 2)) ||" +
            "                GLOBALS.alignNearest(viewportX, viewportX + viewportWidth, viewportWidth, borderLeft, borderRight, viewportX + targetInline, viewportX + targetInline + targetWidth, targetWidth)" +
            "            );" +
            "" +
            "            blockScroll = Math.max(0, blockScroll + viewportY);" +
            "            inlineScroll = Math.max(0, inlineScroll + viewportX);" +
            "        } else {" +
            "            blockScroll = (" +
            "                ((block === 'start') && (targetBlock - _top - borderTop)) ||" +
            "                ((block === 'end') && (targetBlock - bottom + borderBottom + scrollbarHeight)) ||" +
            "                ((block === 'nearest') && GLOBALS.alignNearest(_top, bottom, _height, borderTop, borderBottom + scrollbarHeight, targetBlock, targetBlock + targetHeight, targetHeight)) ||" +
            "                (targetBlock - (_top + _height / 2) + scrollbarHeight / 2)" +
            "            );" +
            "" +
            "            inlineScroll = (" +
            "                ((inline === 'start') && (targetInline - _left - borderLeft)) ||" +
            "                ((inline === 'center') && (targetInline - (_left + _width / 2) + scrollbarWidth / 2)) ||" +
            "                ((inline === 'end') && (targetInline - right + borderRight + scrollbarWidth)) ||" +
            "                GLOBALS.alignNearest(_left, right, _width, borderLeft, borderRight + scrollbarWidth, targetInline, targetInline + targetWidth, targetWidth)" +
            "            );" +
            "" +
            "            var scrollLeft = frame.scrollLeft," +
            "            scrollTop = frame.scrollTop;" +
            "            blockScroll = Math.max(0, Math.min(scrollTop + blockScroll, frame.scrollHeight - _height + scrollbarHeight));" +
            "            inlineScroll = Math.max(0, Math.min(scrollLeft + inlineScroll, frame.scrollWidth - _width + scrollbarWidth));" +
            "            targetBlock += scrollTop - blockScroll;" +
            "            targetInline += scrollLeft - inlineScroll;" +
            "        }" +
            "        computations.push({el: frame, top: blockScroll, left: inlineScroll});" +
            "    }" +
            "" +
            "    return computations;" +
            "};" +
            "" +
            "GLOBALS.isOptionsObject = function isOptionsObjectFunction(options) {" +
            "    return options === Object(options) && Object.keys(options).length !== 0;" +
            "}" +
            "" +
            "GLOBALS.defaultBehavior = function defaultBehaviorFunction(actions, behavior) {" +
            "    var canSmoothScroll = 'scrollBehavior' in document.body.style," +
            "        lBehavior = behavior === void 0 ? 'auto' : behavior;" +
            "" +
            "    actions.forEach(function (_ref) {" +
            "        var el = _ref.el," +
            "            top = _ref.top," +
            "            left = _ref.left;" +
            "" +
            "        if (el.scroll && canSmoothScroll) {" +
            "            el.scroll({" +
            "                top: top," +
            "                left: left," +
            "                behavior: lBehavior" +
            "            });" +
            "        } else {" +
            "           el.scrollTop = top;" +
            "           el.scrollLeft = left;" +
            "        }" +
            "    });" +
            "}" +
            "" +
            "GLOBALS.getOptions = function getOptionsFunction(options) {" +
                General.IF_RETURN("options === false", "{block: 'end', inline: 'nearest'}") +
                General.IF_RETURN("GLOBALS.isOptionsObject(options)", "options") +
                General.RETURN("{block: 'start', inline: 'nearest'}") +
            "}" +
            "" +
            "GLOBALS." + FUNCTION_NAME + " = function scrollIntoView(target, options) {" +
            "    var compute = GLOBALS.compute;" +
            "    var targetIsDetached = !target.ownerDocument.documentElement.contains(target);" +
            "    " + General.IF_RETURN("GLOBALS.isOptionsObject(options) && typeof options.behavior === 'function'", "options.behavior(targetIsDetached ? [] : compute(target, options))") +
            "" +
            "    " + General.IF_RETURN("targetIsDetached", "") +
            "    var computeOptions = GLOBALS.getOptions(options);" +
            "    GLOBALS.defaultBehavior(compute(target, computeOptions), computeOptions.behavior);" +
            "    return true;" +
            "}" +
            GeneralSnippets.RETURN_TRUE +
            "" +
            "\n */"
        );
}
