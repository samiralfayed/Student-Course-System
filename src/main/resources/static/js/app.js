document.addEventListener("DOMContentLoaded", () => {
    const sections = {
        students: document.getElementById("students-section"),
        courses: document.getElementById("courses-section"),
    };

    const navLinks = document.querySelectorAll("aside a");

    navLinks.forEach((link) => {
        link.addEventListener("click", (e) => {
            e.preventDefault();
            const target = link.getAttribute("href").substring(1);

            Object.keys(sections).forEach((key) => {
                sections[key].classList.toggle("hidden", key !== target);
            });

            navLinks.forEach(l => l.classList.remove("bg-blue-100", "border-l-4", "border-blue-600"));
            link.classList.add("bg-blue-100", "border-l-4", "border-blue-600");
        });
    });

    const modalOverlay = document.getElementById("modal-overlay");
    const modalForm = document.getElementById("modal-form");
    const modalTitle = document.getElementById("modal-title");
    const errorBox = document.getElementById("form-error");

    let currentMode = "student";

    document.getElementById("add-student-btn").addEventListener("click", () => showModal("student"));
    document.getElementById("add-course-btn").addEventListener("click", () => showModal("course"));

    function showModal(mode) {
        currentMode = mode;
        modalTitle.textContent = mode === "student" ? "Add Student" : "Add Course";
        modalOverlay.classList.remove("hidden");

        document.getElementById("modal-name").value = "";
        document.getElementById("modal-email").value = "";
        document.getElementById("modal-courses").value = "";
        document.getElementById("modal-description").value = "";

        document.getElementById("modal-email").classList.toggle("hidden", mode !== "student");
        document.getElementById("modal-description").classList.toggle("hidden", mode !== "course");
        errorBox.classList.add("hidden");
    }

    document.getElementById("modal-cancel").addEventListener("click", () => {
        modalOverlay.classList.add("hidden");
    });

    modalForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const name = document.getElementById("modal-name").value.trim();
        const email = document.getElementById("modal-email").value.trim();
        const courseIds = document.getElementById("modal-courses").value
            .split(",")
            .map(id => parseInt(id.trim()))
            .filter(id => !isNaN(id));
        const description = document.getElementById("modal-description").value.trim();

        try {
            let res;
            if (currentMode === "student") {
                res = await fetch("/api/students", {
                    method: "POST",
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ name, email, courseIds })
                });
            } else {
                res = await fetch("/api/courses", {
                    method: "POST",
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ title: name, description, studentIds: courseIds })
                });
            }

            const result = await res.json();
            if (!res.ok) throw new Error(result.message || result);

            modalOverlay.classList.add("hidden");
            currentMode === "student" ? await fetchStudents() : await fetchCourses();
        } catch (err) {
            errorBox.textContent = err.message;
            errorBox.classList.remove("hidden");
        }
    });
});
