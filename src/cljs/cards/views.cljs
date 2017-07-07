(ns cards.views
  (:require
    [cards.components.card :refer [card]]
    [cards.components.new-card-form :refer [new-card-form]]
    [re-frame.core :as re-frame]))

(defn- all-cards [cards]
  [:div.card-columns
   (for [c cards] ^{:key (str (:type c) (:value c))}
     [card c])])

(defn main []
  (let [cards (re-frame/subscribe [:cards])]
    [:div.container
     (all-cards @cards)
     [:div#accordion.navbar.navbar-light.fixed-bottom
      [:div.card
       [:div#filter-cards.w-75.m-auto.collapse.show
        [:a.navbar-toggler.navbar-toggler-right
         {:data-toggle "collapse" :href "#new-card" :data-parent "#accordion"
          :aria-controls "navbar-expanded" :aria-expanded "false" :aria-label "Toggle navigation"}
         [:span.navbar-toggler-icon]]
        [:h2.text-dark.text-center "1"]]
       [:div#new-card.w-75.m-auto.collapse
        [:a.navbar-toggler.navbar-toggler-right
         {:data-toggle "collapse" :href "#filter-cards" :data-parent "#accordion"
          :aria-controls "navbar-expanded" :aria-expanded "false" :aria-label "Toggle navigation"}
         [:span.navbar-toggler-icon]]
        [:h2.text-dark.text-center "2"]
        [new-card-form #(re-frame/dispatch [:new-card %])]]]]]))
