import { createWebHistory, createRouter } from 'vue-router';

const routes =  [
    {
        path: "/",
        name: "SignUpMember",
        component: () => import("./components/SignUpMember"),
    },
    {
        path: "/members",
        name: "ViewAllMembers",
        component: () => import("./components/ViewAllMembers"),
    }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;