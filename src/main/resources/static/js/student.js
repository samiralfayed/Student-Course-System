const API_BASE_URL = "http://localhost:8081/api";

async function fetchStudents() {
    const container = document.getElementById("students-table");
    container.innerHTML = "<p class='text-gray-500'>Loading students...</p>";

    try {
        const res = await fetch(`${API_BASE_URL}/students`);
        const contentType = res.headers.get("Content-Type");

        if (!res.ok || !contentType.includes("application/json")) {
            const text = await res.text();
            throw new Error(`Server Error: ${text}`);
        }

        const data = await res.json();

        if (data.length === 0) {
            container.innerHTML = `<p class="text-gray-500 mt-4">No students found. Click <strong>+ Add Student</strong> to create one.</p>`;
            return;
        }

        let html = `
            <table class="min-w-full border border-gray-200 mt-4">
                <thead class="bg-gray-100">
                    <tr>
                        <th class="p-2 border-b">Name</th>
                        <th class="p-2 border-b">Email</th>
                        <th class="p-2 border-b">Courses</th>
                    </tr>
                </thead>
                <tbody>
        `;

        data.forEach((s) => {
            const courseTitles = s.courses.map((c) => c.title).join(", ");
            html += `
                <tr class="hover:bg-gray-50">
                    <td class="p-2 border-b">${s.name}</td>
                    <td class="p-2 border-b">${s.email}</td>
                    <td class="p-2 border-b">${courseTitles || '-'}</td>
                </tr>
            `;
        });

        html += `</tbody></table>`;
        container.innerHTML = html;
    } catch (error) {
        console.error("Fetch Students Error:", error);
        container.innerHTML = `<p class="text-red-500 mt-4">❌ Failed to load students. Check server or API path.</p>`;
    }
}

document.addEventListener("DOMContentLoaded", fetchStudents);
