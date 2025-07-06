// js/course.js

async function fetchCourses() {
    const res = await fetch("/api/courses");
    const data = await res.json();
    const container = document.getElementById("courses-table");

    let html = `<table class="min-w-full"><thead><tr><th class="p-2">Title</th><th class="p-2">Description</th></tr></thead><tbody>`;
    data.forEach((c) => {
        html += `<tr><td class="p-2">${c.title}</td><td class="p-2">${c.description}</td></tr>`;
    });
    html += `</tbody></table>`;
    container.innerHTML = html;
}

document.addEventListener("DOMContentLoaded", fetchCourses);
