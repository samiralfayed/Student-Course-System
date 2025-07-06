// js/student.js

async function fetchStudents() {
    const res = await fetch("/api/students");
    const data = await res.json();
    const container = document.getElementById("students-table");

    let html = `<table class="min-w-full"><thead><tr><th class="p-2">Name</th><th class="p-2">Email</th><th class="p-2">Courses</th></tr></thead><tbody>`;
    data.forEach((s) => {
        const courseTitles = s.courses.map((c) => c.title).join(", ");
        html += `<tr><td class="p-2">${s.name}</td><td class="p-2">${s.email}</td><td class="p-2">${courseTitles}</td></tr>`;
    });
    html += `</tbody></table>`;
    container.innerHTML = html;
}

document.addEventListener("DOMContentLoaded", fetchStudents);