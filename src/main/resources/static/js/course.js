const API_BASE_URL = "http://localhost:8081/api";

async function fetchCourses() {
    const container = document.getElementById("courses-table");
    container.innerHTML = "<p class='text-gray-500'>Loading courses...</p>";

    try {
        const res = await fetch(`${API_BASE_URL}/courses`);
        const contentType = res.headers.get("Content-Type");

        if (!res.ok || !contentType.includes("application/json")) {
            const text = await res.text();
            throw new Error(`Server Error: ${text}`);
        }

        const data = await res.json();

        if (data.length === 0) {
            container.innerHTML = `<p class="text-gray-500 mt-4">No courses found. Click <strong>+ Add Course</strong> to create one.</p>`;
            return;
        }

        let html = `
            <table class="min-w-full border border-gray-200 mt-4">
                <thead class="bg-gray-100">
                    <tr>
                        <th class="p-2 border-b">Title</th>
                        <th class="p-2 border-b">Description</th>
                    </tr>
                </thead>
                <tbody>
        `;

        data.forEach((c) => {
            html += `
                <tr class="hover:bg-gray-50">
                    <td class="p-2 border-b">${c.title}</td>
                    <td class="p-2 border-b">${c.description || '-'}</td>
                </tr>
            `;
        });

        html += `</tbody></table>`;
        container.innerHTML = html;
    } catch (error) {
        console.error("Fetch Courses Error:", error);
        container.innerHTML = `<p class="text-red-500 mt-4">❌ Failed to load courses. Check server or API path.</p>`;
    }
}

document.addEventListener("DOMContentLoaded", fetchCourses);
