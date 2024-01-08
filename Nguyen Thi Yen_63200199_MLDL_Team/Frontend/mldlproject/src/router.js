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
    },
    {
        path: "/members/:memberNo",
        name: "UpdateMember",
        component: () => import("./components/UpdateMember"),
    },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;