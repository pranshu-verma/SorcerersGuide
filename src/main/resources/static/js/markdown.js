function toMarkdown(text) {
    text = text.replace(/\*\*(.*?)\*\*/g, "<b>$1</b>");
    text = text.replace(/__(.*?)__/g, "<u>$1</u>");
    text = text.replace(/~~(.*?)~~/g, "<i>$1</i>");
    text = text.replace(/--(.*?)--/g, "<del>$1</del>");
    text = text.replace(/(?:\r\n|\r|\n)/g, " <br>");
    text = text.replace(/(?<!!)(https?:\/\/[^\s]+)/g, "<a href='$1'>$1</a>");
    text = text.replace(/!!(.*?)!!/g, "<img src='$1' style='width:100%;'>");
    return text;
}
