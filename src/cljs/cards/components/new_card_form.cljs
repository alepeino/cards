(ns cards.components.new-card-form
  (:require [reagent.core :as r]
            [reagent-forms.core :refer [bind-fields]]
            [cards.components.forms :refer [form-group]]))

(defn- form-template []
  [:form.form-horizontal.text-dark
   (form-group :first-name "First name")
   (form-group :last-name "Last name")
   (form-group :age "Age" :numeric)
   (form-group :email "E-Mail" :email)
   (form-group :comments "Comments" :textarea)])

(defn new-card-form []
  (let [data (r/atom {:first-name "John" :last-name "Doe" :age 35})]
    (fn []
      [:div
       [bind-fields (form-template) data]
       [:label (str @data)]])))
